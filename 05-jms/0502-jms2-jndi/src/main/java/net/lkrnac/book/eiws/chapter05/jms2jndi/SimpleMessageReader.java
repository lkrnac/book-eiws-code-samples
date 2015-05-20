package net.lkrnac.book.eiws.chapter05.jms2jndi;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

public class SimpleMessageReader {
  private JMSContext jmsContext;
  private Queue queue;

  public SimpleMessageReader(JMSContext jmsContext, Queue queue) {
    this.jmsContext = jmsContext;
    this.queue = queue;
  }

  public String readMessage() throws JMSException {
    JMSConsumer jmsConsumer = jmsContext.createConsumer(queue);
    Message message = jmsConsumer.receive(5000);
    return message.getBody(String.class);
  }
}
