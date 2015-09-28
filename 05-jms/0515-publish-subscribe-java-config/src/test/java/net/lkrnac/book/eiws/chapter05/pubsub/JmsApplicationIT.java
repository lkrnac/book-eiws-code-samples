package net.lkrnac.book.eiws.chapter05.pubsub;

import net.lkrnac.book.eiws.chapter05.pubsub.TestingPubSubService.PubSubTuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
@ContextConfiguration(classes = JmsApplication.class)
public class JmsApplicationIT extends
    AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "simple message";

  @Autowired
  private PubSubService pubSubService;

  @Test(timeOut = 3000)
  public void testJms() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN

    // THEN
    TestingPubSubService testingPubSubService =
        (TestingPubSubService) pubSubService;
    PubSubTuple pubSubTuple1 = testingPubSubService.getPubSubTuple();
    Assert.assertEquals(pubSubTuple1.getMessage(), MESSAGE_TEXT);
    PubSubTuple pubSubTuple2 = testingPubSubService.getPubSubTuple();
    Assert.assertEquals(pubSubTuple2.getMessage(), MESSAGE_TEXT);
    Assert.assertNotEquals(pubSubTuple1.getListenerId(),
        pubSubTuple2.getListenerId());
  }
}
