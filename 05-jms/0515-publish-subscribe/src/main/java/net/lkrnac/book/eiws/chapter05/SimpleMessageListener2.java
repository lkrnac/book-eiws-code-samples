package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener2 {
  private PubSubHandler pubSubHandler;

  @Autowired
  public SimpleMessageListener2(PubSubHandler pubSubHandler) {
    super();
    this.pubSubHandler = pubSubHandler;
  }

  @JmsListener(destination = "simpleTopic", subscription = "simpleTopic")
  public void readMessage(String message) {
    pubSubHandler.handleMessage(2, message);
  }
}
