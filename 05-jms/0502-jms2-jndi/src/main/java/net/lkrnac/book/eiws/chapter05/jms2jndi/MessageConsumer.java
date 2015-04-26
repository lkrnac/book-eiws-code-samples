package net.lkrnac.book.eiws.chapter05.jms2jndi;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;

public class MessageConsumer {
  private JMSContext jmsContext;
  private Queue queue;

  public MessageConsumer(JMSContext jmsContext, Queue queue) {
    super();
    this.jmsContext = jmsContext;
    this.queue = queue;
  }

  public String readMessage() throws JMSException {
    JMSConsumer jmsConsumer = jmsContext.createConsumer(queue);
    TextMessage messageReceived = (TextMessage) jmsConsumer.receive(5000);
    return messageReceived.getText();
  }
}
