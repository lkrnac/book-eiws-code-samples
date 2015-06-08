package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.Session;

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

  @JmsListener(destination = "messageQueue")
  public void readMessage(String message, Session session) {
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
