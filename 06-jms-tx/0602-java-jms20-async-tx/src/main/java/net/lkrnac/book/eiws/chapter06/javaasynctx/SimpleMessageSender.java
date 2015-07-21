package net.lkrnac.book.eiws.chapter06.javaasynctx;

import javax.jms.JMSContext;
import javax.jms.Queue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleMessageSender {
  private JMSContext jmsContext;
  private Queue queue;

  public SimpleMessageSender(JMSContext jmsContext, Queue queue) {
    super();
    this.jmsContext = jmsContext;
    this.queue = queue;
  }

  public void sendMessage(String message) {
    log.info("Sending message: {}", message);
    try {
      jmsContext.createProducer().send(queue, message);
      jmsContext.commit();
    } catch (Throwable throwable) {
      jmsContext.rollback();
    }
  }
}
