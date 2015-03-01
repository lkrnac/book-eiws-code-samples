package net.lkrnac.book.eiws.chapter02.httpinvoker.simple;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.FunctionRetryHandler;
import net.lkrnac.book.eiws.ProcessExecutor;
import net.lkrnac.book.eiws.chapter02.httpinvoker.simple.client.ClientConfiguration;
import net.lkrnac.book.eiws.chapter02.httpinvoker.simple.shared.BarService;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { ClientConfiguration.class })
public class HttpInvokerBeanE2ETest extends AbstractTestNGSpringContextTests {
  private static final int RETRY_TIMEOUT = 10000;

  @Test(groups = "maventests")
  public final void testHttiInvoker() throws IOException, InterruptedException {
    Process process =
        new ProcessExecutor().execute("0207-http-invoker-simple-service.jar");
    try {

      FunctionRetryHandler<Object, ApplicationContext> retryHandler =
          new FunctionRetryHandler<>();
      ApplicationContext context =
          retryHandler.retry(SpringApplication::run, ClientConfiguration.class,
              RETRY_TIMEOUT);
      BarService barService = context.getBean(BarService.class);

      String response = barService.serveBar("0207 E2E test");
      assertEquals(response,
          "Bar service 0207 response to parameter: 0207 E2E test");
    } finally {
      process.destroyForcibly();
      process.waitFor();
    }
  }
}
