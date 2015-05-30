package net.lkrnac.book.eiws.chapter05.asyncjndi;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

import net.lkrnac.book.eiws.chapter05.text.SimpleService;

public class AsyncJndiApplication {
  public static void main(String[] args) throws JMSException, NamingException {
    SimpleService simpleService = new SimpleService();
    try (JmsConfiguration jmsConfiguration =
        new JmsConfiguration(simpleService)) {
      jmsConfiguration.init();
      JMSContext jmsContext = jmsConfiguration.getJmsContext();
      Queue queue = jmsConfiguration.getQueue();

      SimpleMessageSender messageSender =
          new SimpleMessageSender(jmsContext, queue);
      messageSender.sendMessage("simple message");
    }
  }
}
