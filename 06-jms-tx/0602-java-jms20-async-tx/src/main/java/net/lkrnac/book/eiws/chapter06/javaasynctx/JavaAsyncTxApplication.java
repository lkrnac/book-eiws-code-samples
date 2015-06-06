package net.lkrnac.book.eiws.chapter06.javaasynctx;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.NamingException;

import net.lkrnac.book.eiws.chapter05.text.SimpleService;

public class JavaAsyncTxApplication {
  public static void main(String[] args) throws NamingException,
      InterruptedException {
    SimpleService simpleService = new SimpleService();
    try (JmsConfiguration jmsConfiguration =
        new JmsConfiguration(simpleService)) {
      jmsConfiguration.init();
      JMSContext jmsContext = jmsConfiguration.getWriteContext();
      Queue queue = jmsConfiguration.getQueue();

      SimpleMessageSender messageSender =
          new SimpleMessageSender(jmsContext, queue);
      messageSender.sendMessage("simple message");
    }
  }
}
