package net.lkrnac.book.eiws.chapter02.rmi.java;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.rmi.NotBoundException;

import net.lkrnac.book.eiws.ProcessExecutor;

import org.testng.annotations.Test;

public class JavaRmiE2ETest {
  @Test
  public void parformJavaRmiE2ETest() throws IOException, InterruptedException,
      NotBoundException {
    Process process = null;
    try {
      process = new ProcessExecutor().execute("0201-java-rmi-service.jar");
      Thread.sleep(2000);

      String response = new FooClient().callService("E2E test");
      assertEquals(response, "Bar service reponse to parameter: E2E test");
    } finally {
      process.destroyForcibly();
    }
  }
}
