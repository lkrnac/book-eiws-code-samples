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
      messageSender.sendMessage("simple message");

      SimpleMessageReader messageReader = new SimpleMessageReader();
      messageReader.init(connection, queue);
      String message = messageReader.readMessage();

      log.info("Message Received: {}", message);
    }
  }
}
