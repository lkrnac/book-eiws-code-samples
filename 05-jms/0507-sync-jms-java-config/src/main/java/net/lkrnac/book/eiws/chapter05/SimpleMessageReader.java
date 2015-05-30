package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageReader {
  private JmsTemplate jmsTemplate;
  private SimpleService simpleMessageHandler;

  @Autowired
  public SimpleMessageReader(JmsTemplate jmsTemplate,
      SimpleService simpleMessageHandler) {
    super();
    this.jmsTemplate = jmsTemplate;
    this.simpleMessageHandler = simpleMessageHandler;
  }

  @Scheduled(fixedRate = 1200L)
  public void readMessage() {
    String message = (String) jmsTemplate.receiveAndConvert("messageQueue");
    simpleMessageHandler.processText(message);
  }
}
