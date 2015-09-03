package net.lkrnac.book.eiws.chapter07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener {
  private SimpleService simpleService;
  private boolean errorSimulated = false;

  @Autowired
  public SimpleMessageListener(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  @JmsListener(destination = "ExpiryQueue")
  public void readMessage(String message) {
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
