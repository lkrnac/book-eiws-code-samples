package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.JmsPublishSubscribeApplicationTests.TestPubSubConfiguration;
import net.lkrnac.book.eiws.chapter05.TestingPubSubHandler.PubSubTuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { JmsPublishSubscribeApplication.class,
    TestPubSubConfiguration.class })
public class JmsPublishSubscribeApplicationTests extends
    AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "simple message";

  @Autowired
  private PubSubHandler pubSubHandler;

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Configuration
  public static class TestPubSubConfiguration {
    @Bean
    @Primary
    @Profile("integration-test")
    public PubSubHandler pubSubHandler() {
      return new TestingPubSubHandler();
    }
  }

  @Test(timeOut = 3000)
  public void contextLoads() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN

    // THEN
    TestingPubSubHandler testingPubSubHandler =
        (TestingPubSubHandler) pubSubHandler;
    PubSubTuple pubSubTuple1 = testingPubSubHandler.getPubSubTuple();
    Assert.assertEquals(pubSubTuple1.getMessage(), MESSAGE_TEXT);
    PubSubTuple pubSubTuple2 = testingPubSubHandler.getPubSubTuple();
    Assert.assertEquals(pubSubTuple2.getMessage(), MESSAGE_TEXT);
    Assert.assertNotEquals(pubSubTuple1.getListenerId(),
        pubSubTuple2.getListenerId());
  }
}
