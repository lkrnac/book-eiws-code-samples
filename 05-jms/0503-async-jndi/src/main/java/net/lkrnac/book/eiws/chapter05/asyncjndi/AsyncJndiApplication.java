package net.lkrnac.book.eiws.chapter05.asyncjndi;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

import net.lkrnac.book.eiws.chapter05.test.SimpleMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOG = LoggerFactory
        .getLogger(MessageHandler.class);

    @Override
    public void handleMessage(String message) {
      LOG.info("Message received: {}", message);
    }
  }
}
