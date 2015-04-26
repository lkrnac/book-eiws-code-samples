package net.lkrnac.book.eiws.chapter05.jms11jndi;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Jms11JndiApplicationIT {
  private static final String MESSAGE_TEXT = "dummyMessage";

  @Test(groups = "maventests")
  public void queueTest() throws NamingException, JMSException {
    try (JmsConfiguration jmsConfiguration = new JmsConfiguration()) {
      JMSContext jmsContext = jmsConfiguration.getJmsContext();
      Queue queue = jmsConfiguration.getQueue();

      // WHEN
      MessageSender messageSender = new MessageSender(jmsContext, queue);
      messageSender.sendMessage(MESSAGE_TEXT);

      MessageConsumer messageConsumer = new MessageConsumer(jmsContext, queue);
      String actualMessage = messageConsumer.readMessage();

      // THEN
      Assert.assertEquals(MESSAGE_TEXT, actualMessage);
    }
  }
}
