package net.lkrnac.book.eiws.chapter07;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
// @Transactional
public class SimpleMessageSender {
  private static final String SIMPLE_MESSAGE = "simple message";
  private JmsTemplate jmsTemplate;

  @Autowired
  public SimpleMessageSender(JmsTemplate jmsTemplate) {
    super();
    this.jmsTemplate = jmsTemplate;
  }

  @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
  public void send() {
    log.info("Sending message: {}", SIMPLE_MESSAGE);
    jmsTemplate.convertAndSend("ExpiryQueue", SIMPLE_MESSAGE);
  }
}
