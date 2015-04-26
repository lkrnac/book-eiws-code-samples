package net.lkrnac.book.eiws.chapter05.jms11jndi;

import javax.jms.Connection;
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
      jmsConfiguration.init();
      Connection connection = jmsConfiguration.getConnection();
      Queue queue = jmsConfiguration.getQueue();
      connection.start();

      // WHEN
      SimpleMessageSender messageSender =
          new SimpleMessageSender(connection, queue);
      messageSender.sendMessage(MESSAGE_TEXT);

      SimpleMessageReader messageReader =
          new SimpleMessageReader(connection, queue);
      String actualMessage = messageReader.readMessage();

      // THEN
      Assert.assertEquals(MESSAGE_TEXT, actualMessage);
    }
  }
}
