package net.lkrnac.book.eiws.chapter06.javasynctx;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test is relies on separate HornetQ server. During build Maven runs it
 * via hornetq-maven-plugin.
 */
public class JavaSyncTxApplicationIT {
  private static final String MESSAGE_TEXT1 = "dummyMessage1";
  private static final String MESSAGE_TEXT2 = "dummyMessage2";

  @Test(groups = "maventests")
  public void queueTest() throws NamingException, JMSException {
    // GIVEN
    try (JmsConfiguration jmsConfiguration = new JmsConfiguration()) {
      jmsConfiguration.init();
      Connection connection = jmsConfiguration.getConnection();
      Queue queue = jmsConfiguration.getQueue();
      connection.start();

      // WHEN
      SimpleMessageSender messageSender = new SimpleMessageSender();
      messageSender.init(connection, queue);
      messageSender.sendMessages(MESSAGE_TEXT1, MESSAGE_TEXT2);

      SimpleMessageReader messageReader = new SimpleMessageReader();
      messageReader.init(connection, queue);
      String actualMessage1 = messageReader.readMessage();
      String actualMessage2 = messageReader.readMessage();
      messageReader.finishReading();

      // THEN
      Assert.assertEquals(MESSAGE_TEXT1, actualMessage1);
      Assert.assertEquals(MESSAGE_TEXT2, actualMessage2);
    }
  }
}
