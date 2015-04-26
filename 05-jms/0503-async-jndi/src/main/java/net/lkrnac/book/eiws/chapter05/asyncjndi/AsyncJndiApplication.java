package net.lkrnac.book.eiws.chapter05.asyncjndi;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

public class AsyncJndiApplication {
  public static void main(String[] args) throws JMSException, NamingException {
    MessageHandler messageHandler = new MessageHandler();
    try (JmsConfiguration jmsConfiguration =
        new JmsConfiguration(messageHandler)) {
      jmsConfiguration.init();
      JMSContext jmsContext = jmsConfiguration.getJmsContext();
      Queue queue = jmsConfiguration.getQueue();

      SimpleMessageSender messageSender =
          new SimpleMessageSender(jmsContext, queue);
      messageSender.sendMessage("Hello World!");
    }
  }

  private static class MessageHandler implements SimpleMessageHandler {
    @Override
    public void handleMessage(String message) {
      System.out.println("Message received: " + message);
    }
  }
}
