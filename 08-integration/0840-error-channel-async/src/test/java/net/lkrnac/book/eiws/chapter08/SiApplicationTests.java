package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceVoid;
import net.lkrnac.book.eiws.chapter08.out.ErrorHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private SiWrapperServiceVoid wrapperService;

  @Autowired
  private ErrorHandler errorHandler;

  @Test
  public void testSi() throws InterruptedException {
    // GIVEN

    // WHEN
    wrapperService.processText("simple message");

    // THEN
    // wait for async flow
    Thread.sleep(100);

    TestErrorHandler testErrorHandler = (TestErrorHandler) errorHandler;
    Assert.assertEquals(testErrorHandler.getLastError().getClass(),
        MessageHandlingException.class);
  }
}
