package net.lkrnac.book.eiws.chapter05.asyncjndi;

import javax.jms.JMSContext;
import javax.jms.Queue;

import net.lkrnac.book.eiws.chapter05.test.simplemessage.TestingSimpleService;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test is relies on separate HornetQ server. During build Maven runs it
 * via hornetq-maven-plugin.
 */
public class AsyncJndiApplicationIT {
  private static final String MESSAGE_TEXT = "dummyMessage";

  @Test(groups = "maventests")
  public void queueTest() throws Exception {
    // GIVEN
    TestingSimpleService messageHandler =
        new TestingSimpleService();
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
