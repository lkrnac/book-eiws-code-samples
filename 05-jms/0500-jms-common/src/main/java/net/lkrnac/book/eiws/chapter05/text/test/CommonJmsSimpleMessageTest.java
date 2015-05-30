package net.lkrnac.book.eiws.chapter05.text.test;

import net.lkrnac.book.eiws.chapter05.text.SimpleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonJmsSimpleMessageTest extends
    AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "simple message";

  @Autowired
  private SimpleService simpleService;

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Test(timeOut = 3000)
  public void contextLoads() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN

    // THEN
    TestingSimpleService testingSimpleService =
        (TestingSimpleService) simpleService;
    Assert.assertEquals(testingSimpleService.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(testingSimpleService.getMessage(), MESSAGE_TEXT);
  }
}
