package net.lkrnac.book.eiws.chapter06.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener {
  private SimpleService simpleService;

  @Autowired
  public SimpleMessageListener(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessage(String message) {
    simpleService.processText(message);
  }

  @JmsListener(destination = "messageQueueLost")
  public void readMessageLost(String message) {
    if ("simple message lost".equals(message)) {
      throw new IllegalArgumentException(message);
    }
    simpleService.processText(message);
  }

}
