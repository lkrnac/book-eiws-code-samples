package net.lkrnac.book.eiws.chapter05.jms2jndi;

import javax.jms.JMSContext;
import javax.jms.Queue;

public class SimpleMessageSender {
  private JMSContext jmsContext;
  private Queue queue;

  public SimpleMessageSender(JMSContext jmsContext, Queue queue) {
    this.jmsContext = jmsContext;
    this.queue = queue;
  }

  public void sendMessage(String message) {
    jmsContext.createProducer().send(queue, message);
  }
}
