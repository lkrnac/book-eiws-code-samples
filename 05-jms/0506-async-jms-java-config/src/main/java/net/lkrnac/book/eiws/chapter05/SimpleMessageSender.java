package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageSender {
  private JmsTemplate jmsTemplate;

  @Autowired
  public SimpleMessageSender(JmsTemplate jmsTemplate) {
    super();
    this.jmsTemplate = jmsTemplate;
  }

  public void send(String message) {
    this.jmsTemplate.convertAndSend("messageQueue", message);
  }
}
