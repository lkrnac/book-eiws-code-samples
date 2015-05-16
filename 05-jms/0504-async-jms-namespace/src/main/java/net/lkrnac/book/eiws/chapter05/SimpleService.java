package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleService {
  private SimpleMessageHandler simpleMessageHandler;

  @Autowired
  public SimpleService(SimpleMessageHandler simpleMessageHandler) {
    super();
    this.simpleMessageHandler = simpleMessageHandler;
  }

  public void handleMessage(String message) {
    simpleMessageHandler.handleMessage(message);
  }
}
