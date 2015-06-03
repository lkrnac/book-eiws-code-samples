package net.lkrnac.book.eiws.chapter06.javasynctx;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaSyncTxApplication {
  public static void main(String[] args) throws JMSException, NamingException {
    try (JmsConfiguration jmsConfiguration = new JmsConfiguration()) {
      jmsConfiguration.init();
      Queue queue = jmsConfiguration.getQueue();
      Connection connection = jmsConfiguration.getConnection();
      connection.start();

      SimpleMessageSender messageSender = new SimpleMessageSender();
      messageSender.init(connection, queue);
      messageSender.sendMessages("simple message1", "simple message2");

      SimpleMessageReader messageReader = new SimpleMessageReader();
      messageReader.init(connection, queue);
      String message1 = messageReader.readMessage();
      log.info("Message Received: {}", message1);
      String message2 = messageReader.readMessage();
      log.info("Message Received: {}", message2);

      messageReader.rollbackReading();

      String message3 = messageReader.readMessage();
      log.info("Message Received: {}", message3);
      String message4 = messageReader.readMessage();
      log.info("Message Received: {}", message4);

      messageReader.finishReading();
    }
  }
}
