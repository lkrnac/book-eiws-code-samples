package net.lkrnac.book.eiws.chapter05.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    jmsTemplate.setReceiveTimeout(1000);
  }

  @Scheduled(fixedRate = 1200)
  public void readMessage() {
    String message = (String) jmsTemplate.receiveAndConvert("messageQueue");
    simpleService.processText(message);
  }
}
