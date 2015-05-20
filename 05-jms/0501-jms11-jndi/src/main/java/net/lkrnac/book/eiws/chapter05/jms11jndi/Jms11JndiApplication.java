package net.lkrnac.book.eiws.chapter05.jms11jndi;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

public class Jms11JndiApplication {
  public static void main(String[] args) throws JMSException, NamingException {
    try (JmsConfiguration jmsConfiguration = new JmsConfiguration()) {
      jmsConfiguration.init();
      Queue queue = jmsConfiguration.getQueue();
      Connection connection = jmsConfiguration.getConnection();
      connection.start();

      SimpleMessageSender messageSender = new SimpleMessageSender();
      messageSender.init(connection, queue);
      messageSender.sendMessage("Hello World!");

      SimpleMessageReader messageReader = new SimpleMessageReader();
      messageReader.init(connection, queue);
      String message = messageReader.readMessage();

      System.out.println("Message Received: " + message);
    }
  }
}
