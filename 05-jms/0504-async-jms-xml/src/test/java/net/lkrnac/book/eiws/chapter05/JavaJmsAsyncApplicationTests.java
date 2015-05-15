package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.test.TestingMessageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

//@SpringApplicationConfiguration(locations = "classpath:spring-jms.xml", classes = TestConfiguration.class)
public class JavaJmsAsyncApplicationTests extends
    AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "simple message";

  @Autowired
  private SimpleMessageHandler simpleMessageHandler;

  @Autowired
  private SimpleMessageSender simpleMessageSender;

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Configuration
  public static class TestConfiguration {
    @Bean
    @Profile("integration-test")
    public SimpleMessageHandler simpleMessageHandler() {
      return new TestingMessageHandler();
    }
  }

  @Test
  public void contextLoads() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN
    simpleMessageSender.send(MESSAGE_TEXT);

    // THEN
    TestingMessageHandler testingMessageHandler =
        (TestingMessageHandler) simpleMessageHandler;
    Assert.assertEquals(testingMessageHandler.getMessage(), MESSAGE_TEXT);
  }

}
