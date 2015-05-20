package net.lkrnac.book.eiws.chapter05.jms2jndi;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jms2JndiApplication {
  public static void main(String[] args) throws JMSException, NamingException {
    try (JmsConfiguration jmsConfiguration = new JmsConfiguration()) {
      jmsConfiguration.init();
      JMSContext jmsContext = jmsConfiguration.getJmsContext();
      Queue queue = jmsConfiguration.getQueue();

      SimpleMessageSender messageSender = new SimpleMessageSender();
      messageSender.init(jmsContext, queue);
      messageSender.sendMessage("Hello World!");

      SimpleMessageReader messageConsumer = new SimpleMessageReader();
      messageConsumer.init(jmsContext, queue);
      String message = messageConsumer.readMessage();

      log.info("Message Received: {}", message);
    }
  }
}
