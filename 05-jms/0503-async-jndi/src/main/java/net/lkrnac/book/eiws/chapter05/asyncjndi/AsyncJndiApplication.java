package net.lkrnac.book.eiws.chapter05.asyncjndi;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter05.SimpleService;

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
      messageSender.sendMessage("simple message");
    }
  }

  @Slf4j
  private static class MessageHandler implements SimpleService {
    @Override
    public void processText(String message) {
      log.info("Message received: {}", message);
    }
  }
}
