package net.lkrnac.book.eiws.chapter05.text.test;

import net.lkrnac.book.eiws.chapter05.text.SimpleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
public class CommonJmsSimpleMessageTest extends
    AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "simple message";

  @Autowired
  private SimpleService simpleService;

  @Test(timeOut = 3000)
  public void testJms() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN

    // THEN
    TestingSimpleService testingSimpleService =
        (TestingSimpleService) simpleService;
    Assert.assertEquals(testingSimpleService.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(testingSimpleService.getMessage(), MESSAGE_TEXT);
  }
}
