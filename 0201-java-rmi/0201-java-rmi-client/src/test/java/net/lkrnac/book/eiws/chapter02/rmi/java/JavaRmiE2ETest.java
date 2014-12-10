package net.lkrnac.book.eiws.chapter02.rmi.java;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.ProcessExecutor;
import net.lkrnac.book.eiws.RetryHandler;

import org.testng.annotations.Test;

public class JavaRmiE2ETest {
  @Test
  public void parformJavaRmiE2ETest() throws IOException {
    Process process = null;
    try {
      process = new ProcessExecutor().execute("0201-java-rmi-service.jar");

      RetryHandler<String, String> retryHandler = new RetryHandler<String, String>();
      String response = retryHandler.retry(new FooClient()::callService,
          "E2E test", 3000);
      assertEquals(response, "Bar service response to parameter: E2E test");
    } finally {
      process.destroyForcibly();
    }
  }
}
