package net.lkrnac.book.eiws.chapter05.jms2jndi;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

public class Jms2JndiApplication {
  public static void main(String[] args) throws JMSException, NamingException {
    try (JmsConfiguration jmsConfiguration = new JmsConfiguration()) {
      JMSContext jmsContext = jmsConfiguration.getJmsContext();
      Queue queue = jmsConfiguration.getQueue();

      MessageSender messageSender = new MessageSender(jmsContext, queue);
      messageSender.sendMessage("Hello World!");

      MessageConsumer messageConsumer = new MessageConsumer(jmsContext, queue);
      String message = messageConsumer.readMessage();

      System.out.println("Message Received: " + message);
    }
  }
}
