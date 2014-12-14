package net.lkrnac.book.eiws.chapter02.rmi.spring.javaconfig;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.ProcessExecutor;
import net.lkrnac.book.eiws.RetryHandler;
import net.lkrnac.book.eiws.chapter02.rmi.spring.javaconfig.client.BarService;
import net.lkrnac.book.eiws.chapter02.rmi.spring.javaconfig.client.ClientConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

public class SpringRmiJavaConfigE2ETest {
  private static final int RETRY_TIMEOUT = 4000;

  @Test
  public void testRmiCall() throws IOException, InterruptedException {
    Process process = new ProcessExecutor()
        .execute("0202-spring-rmi-java-config-service.jar");
    try {

      RetryHandler<Object, ApplicationContext> retryHandler = new RetryHandler<>();
      ApplicationContext context = retryHandler.retry(SpringApplication::run,
          ClientConfiguration.class, RETRY_TIMEOUT);
      BarService barService = context.getBean(BarService.class);

      String response = barService.serveBar("0202 E2E test");
      assertEquals(response,
          "Bar service 0202 reponse to parameter: 0202 E2E test");
    } finally {
      process.destroyForcibly();
      process.waitFor();
    }
  }
}
