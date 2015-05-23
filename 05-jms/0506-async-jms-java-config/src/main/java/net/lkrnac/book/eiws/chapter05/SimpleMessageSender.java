package net.lkrnac.book.eiws.chapter05;

import javax.jms.Queue;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleMessageSender {
  private static final String SIMPLE_MESSAGE = "simple message";
  private JmsTemplate jmsTemplate;
  private Queue queue;

  @Autowired
  public SimpleMessageSender(JmsTemplate jmsTemplate, Queue queue) {
    super();
    this.jmsTemplate = jmsTemplate;
    this.queue = queue;
  }

  @Scheduled(fixedRate = 1000L)
  public void send() {
    log.info("Sending message: {}", SIMPLE_MESSAGE);
    jmsTemplate.convertAndSend(queue, SIMPLE_MESSAGE);
  }
}
