package net.lkrnac.book.eiws.chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceVoid;

@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  @Autowired
  private SiWrapperServiceVoid wrapperService;

  @Test
  public void testSi() {
    // GIVEN - spring configuration

    // WHEN
    wrapperService.processText("message1");
    wrapperService.processText("message2");

    // THEN - nothing as we are discarding messages
  }
}
