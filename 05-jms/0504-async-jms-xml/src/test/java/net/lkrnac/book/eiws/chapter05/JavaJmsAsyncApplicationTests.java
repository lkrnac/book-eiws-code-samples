package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.JavaJmsAsyncApplicationTests.TestConfiguration;
import net.lkrnac.book.eiws.chapter05.test.TestingMessageHandler;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { JavaJmsAsyncApplication.class,
    TestConfiguration.class })
public class JavaJmsAsyncApplicationTests {
  private static final String MESSAGE_TEXT = "Hello";

  @Autowired
  private SimpleMessageHandler simpleMessageHandler;

  @BeforeClass
  public static void init() {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Configuration
  public static class TestConfiguration {
    @Bean
    @Primary
    @Profile("integration-test")
    public SimpleMessageHandler simpleMessageHandler() {
      return new TestingMessageHandler();
    }
  }

  @Test
  public void contextLoads() throws InterruptedException {
    // GIVEN

    // WHEN

    // THEN
    TestingMessageHandler testingMessageHandler =
        (TestingMessageHandler) simpleMessageHandler;
    Assert.assertEquals(testingMessageHandler.getMessage(), MESSAGE_TEXT);
  }

}
