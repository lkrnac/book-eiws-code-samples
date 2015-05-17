package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener {
  private SimpleMessageHandler simpleMessageHandler;

  @Autowired
  public SimpleMessageListener(SimpleMessageHandler simpleMessageHandler) {
    super();
    this.simpleMessageHandler = simpleMessageHandler;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessage(String message) {
    simpleMessageHandler.handleMessage(message);
  }
}
