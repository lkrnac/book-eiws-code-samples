package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
  @Autowired
  private JmsTemplate jmsTemplate;

  @Scheduled(fixedDelay = 1000L)
  public void send() {
    this.jmsTemplate.convertAndSend("messageQueue", "Hello");
  }
}
