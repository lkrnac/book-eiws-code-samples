package net.lkrnac.book.eiws.chapter02.rmi.spring;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.ProcessExecutor;
import net.lkrnac.book.eiws.RetryHandler;
import net.lkrnac.book.eiws.chapter02.rmi.spring.xmlconfig.client.BarService;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

public class SpringRmiXmlConfigE2ETest {

  private static final int RETRY_TIMEOUT = 4000;

  @Test
  public void testRmiCall() throws IOException, InterruptedException {
    Process process =
        new ProcessExecutor().execute("0203-spring-rmi-xml-config-service.jar");
    try {

      RetryHandler<Object, ApplicationContext> retryHandler =
          new RetryHandler<>();
      ApplicationContext context =
          retryHandler.retry(SpringApplication::run, new ClassPathResource(
              "foo-client-context.xml"), RETRY_TIMEOUT);
      BarService barService = context.getBean(BarService.class);

      String response = barService.serveBar("0203 E2E test");
      assertEquals(response,
          "Bar service 0203 response to parameter: 0203 E2E test");
    } finally {
      process.destroyForcibly();
      process.waitFor();
    }
  }
}
