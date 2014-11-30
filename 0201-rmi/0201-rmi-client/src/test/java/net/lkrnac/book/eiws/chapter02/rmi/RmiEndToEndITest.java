package net.lkrnac.book.eiws.chapter02.rmi;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter02.rmi.client.BarService;
import net.lkrnac.book.eiws.chapter02.rmi.client.ClientConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

public class RmiEndToEndITest {

  @Test
  public void testRmiCall() throws IOException, InterruptedException {
    Process process = null;
    try {
      process = new ProcessExecutor().execute("0201-rmi-service.jar");
      Thread.sleep(4000);

      ApplicationContext context = SpringApplication
          .run(ClientConfiguration.class);
      BarService barService = context.getBean(BarService.class);

      String response = barService.serveBar("E2E test");
      assertEquals(response, "Bar service reponse to parameter: E2E test");
    } finally {
      process.destroyForcibly();
    }
  }
}
