package net.lkrnac.book.eiws.chapter02.rmi.java;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.ProcessExecutor;
import net.lkrnac.book.eiws.RetryHandler;

import org.testng.annotations.Test;

public class JavaRmiE2ETest {
  private static final int RETRY_TIMEOUT = 10000;

  @Test
  public final void parformJavaRmiE2ETest() throws IOException,
      InterruptedException {
    Process process = null;
    try {
      process = new ProcessExecutor().execute("0201-java-rmi-service.jar");

      RetryHandler<String, String> retryHandler = new RetryHandler<String, String>();
      String response = retryHandler.retry(new FooClient()::callService,
          "Java RMI E2E test", RETRY_TIMEOUT);
      assertEquals(response,
          "Bar service (Java RMI) response to parameter: Java RMI E2E test");
    } finally {
      process.destroyForcibly();
      process.waitFor();
    }
  }
}
