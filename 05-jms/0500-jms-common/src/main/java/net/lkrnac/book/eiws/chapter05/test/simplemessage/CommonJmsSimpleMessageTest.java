package net.lkrnac.book.eiws.chapter05.test.simplemessage;

import net.lkrnac.book.eiws.chapter05.SimpleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
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

  @Configuration
  public static class TestSimpleMessageConfiguration {
    @Bean
    @Primary
    @Profile("integration-test")
    public SimpleService simpleService() {
      return new TestingSimpleService();
    }
  }

  @Test(timeOut = 3000)
  public void contextLoads() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN

    // THEN
    TestingSimpleService testingMessageHandler =
        (TestingSimpleService) simpleService;
    Assert.assertEquals(testingMessageHandler.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(testingMessageHandler.getMessage(), MESSAGE_TEXT);
  }
}
