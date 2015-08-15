package net.lkrnac.book.eiws.chapter08;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "simple message";

  @Autowired
  private SimpleService simpleService;

  @Test(expectedExceptions = IllegalStateException.class)
  public void testSi() throws InterruptedException, IOException {
    // GIVEN

    // WHEN
    simpleService.processText(MESSAGE_TEXT);

    // THEN - see test annotation
  }
}
