package net.lkrnac.book.eiws.chapter05.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener2 {
  private PubSubService pubSubService;

  @Autowired
  public SimpleMessageListener2(PubSubService pubSubService) {
    super();
    this.pubSubService = pubSubService;
  }

  @JmsListener(destination = "simpleTopic", subscription = "simpleTopic")
  public void readMessage(String message) {
    pubSubService.handleMessage(2, message);
  }
}
