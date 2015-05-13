package net.lkrnac.book.eiws.chapter05.asyncjndi;

import javax.jms.JMSContext;
import javax.jms.Queue;

import net.lkrnac.book.eiws.chapter05.test.TestingMessageHandler;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AsyncJndiApplicationIT {
  private static final String MESSAGE_TEXT = "dummyMessage";

  @Test(groups = "maventests")
  public void queueTest() throws Exception {
    // GIVEN
    TestingMessageHandler messageHandler = new TestingMessageHandler();
    try (JmsConfiguration jmsConfiguration =
        new JmsConfiguration(messageHandler)) {
      jmsConfiguration.init();
      JMSContext jmsContext = jmsConfiguration.getJmsContext();
      Queue queue = jmsConfiguration.getQueue();

      // WHEN
      SimpleMessageSender messageSender =
          new SimpleMessageSender(jmsContext, queue);
      messageSender.sendMessage(MESSAGE_TEXT);

      // THEN
      Assert.assertEquals(messageHandler.getMessage(), MESSAGE_TEXT);
    }
  }
}
