package net.lkrnac.book.eiws.chapter06.text;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleMessageSender {
  private static final String SIMPLE_MESSAGE_DUPLICATE =
      "simple message duplicate";
  private JmsTemplate jmsTemplate;

  @Autowired
  public SimpleMessageSender(JmsTemplate jmsTemplate) {
    super();
    this.jmsTemplate = jmsTemplate;
  }

  @Scheduled(fixedDelay = Long.MAX_VALUE)
  public void sendDuplicate() {
    log.info("Sending message: {}", SIMPLE_MESSAGE_DUPLICATE);
    jmsTemplate.convertAndSend("messageQueueDuplicate",
        SIMPLE_MESSAGE_DUPLICATE);
  }
}
