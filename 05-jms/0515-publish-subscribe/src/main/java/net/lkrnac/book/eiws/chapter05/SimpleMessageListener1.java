package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener1 {
  private PubSubHandler pubSubHandler;

  @Autowired
  public SimpleMessageListener1(PubSubHandler pubSubHandler) {
    super();
    this.pubSubHandler = pubSubHandler;
  }

  @JmsListener(destination = "simpleTopic")
  public void readMessage(String message) {
    pubSubHandler.handleMessage(1, message);
  }
}
