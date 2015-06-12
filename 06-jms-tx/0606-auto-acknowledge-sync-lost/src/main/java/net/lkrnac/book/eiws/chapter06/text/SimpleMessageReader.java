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
  private boolean errorSimulated = false;

  @Autowired
  public SimpleMessageReader(JmsTemplate jmsTemplate,
      SimpleService simpleService) {
    super();
    this.jmsTemplate = jmsTemplate;
    this.simpleService = simpleService;
  }

  @Scheduled(fixedRate = Long.MAX_VALUE)
  public void readMessage() {
    String message = (String) jmsTemplate.receiveAndConvert("messageQueue");
    log.info("Message read: {}", message);

    preprocess(message);
    simpleService.processText(message);
  }

  private void preprocess(String message) {
    // simulate error
    if (!errorSimulated) {
      errorSimulated = true;
      throw new IllegalArgumentException(message);
    }
  }
}
