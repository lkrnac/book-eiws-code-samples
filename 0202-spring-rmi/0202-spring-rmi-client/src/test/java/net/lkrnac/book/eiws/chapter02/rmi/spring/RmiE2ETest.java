package net.lkrnac.book.eiws.chapter02.rmi.spring;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter02.rmi.spring.client.BarService;
import net.lkrnac.book.eiws.chapter02.rmi.spring.client.ClientConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

public class RmiE2ETest {

  @Test
  public void testRmiCall_JavaConfig() throws IOException, InterruptedException {
    performE2ETEstOnContext(ClientConfiguration.class);
  }

  @Test
  public void testRmiCall_XmlConfig() throws IOException, InterruptedException {
    performE2ETEstOnContext(new ClassPathResource("foo-client-context.xml"));
  }

  private void performE2ETEstOnContext(Object contextToTest)
      throws IOException, InterruptedException {
    Process process = null;
    try {
      process = new ProcessExecutor().execute("0202-spring-rmi-service.jar");
      Thread.sleep(2000);

      ApplicationContext context = SpringApplication.run(contextToTest);
      BarService barService = context.getBean(BarService.class);

      String response = barService.serveBar("E2E test");
      assertEquals(response, "Bar service reponse to parameter: E2E test");
    } finally {
      process.destroyForcibly();
    }
  }
}
