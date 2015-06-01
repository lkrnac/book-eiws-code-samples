package net.lkrnac.book.eiws.chapter06.javaasynctx;

import javax.jms.JMSContext;
import javax.jms.Queue;

public class SimpleMessageSender {
  private JMSContext jmsContext;
  private Queue queue;

  public SimpleMessageSender(JMSContext jmsContext, Queue queue) {
    super();
    this.jmsContext = jmsContext;
    this.queue = queue;
  }

  public void sendMessage(String message) {
    jmsContext.createProducer().send(queue, message);
    jmsContext.commit();
  }
}
