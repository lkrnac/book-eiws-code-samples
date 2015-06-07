package net.lkrnac.book.eiws.chapter06.text;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleMessageReader {
  private JmsTemplate jmsTemplate;
  private SimpleService simpleService;

  @Autowired
  public SimpleMessageReader(JmsTemplate jmsTemplate,
      SimpleService simpleService) {
    super();
    this.jmsTemplate = jmsTemplate;
    this.simpleService = simpleService;
  }

  @Scheduled(fixedRate = Long.MAX_VALUE)
  public void readMessage() {
    log.info("Message read start");
    String message = (String) jmsTemplate.receiveAndConvert("messageQueue");
    log.info("Message read: {}", message);

    // simulate error
    if ("simple message".equals(message)) {
      log.info("Simulate error!");
      throw new IllegalArgumentException(message);
    }

    simpleService.processText(message);
  }
}
