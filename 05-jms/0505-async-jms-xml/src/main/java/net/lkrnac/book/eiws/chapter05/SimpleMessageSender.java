package net.lkrnac.book.eiws.chapter05;

import org.springframework.jms.core.JmsTemplate;

public class SimpleMessageSender {
  private JmsTemplate jmsTemplate;

  public SimpleMessageSender(JmsTemplate jmsTemplate) {
    super();
    this.jmsTemplate = jmsTemplate;
  }

  public void send(String message) {
    this.jmsTemplate.convertAndSend("messageQueue", message);
  }
}
