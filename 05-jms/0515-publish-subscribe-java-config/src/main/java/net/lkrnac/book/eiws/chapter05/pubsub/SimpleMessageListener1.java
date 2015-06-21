package net.lkrnac.book.eiws.chapter05.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener1 {
  private PubSubService pubSubService;

  @Autowired
  public SimpleMessageListener1(PubSubService pubSubService) {
    super();
    this.pubSubService = pubSubService;
  }

  @JmsListener(destination = "simpleTopic")
  public void readMessage(String message) {
    pubSubService.processText(1, message);
  }
}
