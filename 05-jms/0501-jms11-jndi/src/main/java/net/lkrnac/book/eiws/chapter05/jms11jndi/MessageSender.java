package net.lkrnac.book.eiws.chapter05.jms11jndi;

import javax.jms.JMSContext;
import javax.jms.Queue;

public class MessageSender {
  private JMSContext jmsContext;
  private Queue queue;

  public MessageSender(JMSContext jmsContext, Queue queue) {
    super();
    this.jmsContext = jmsContext;
    this.queue = queue;
  }

  public void sendMessage(String message) {
    jmsContext.createProducer().send(queue, message);
  }
}
