package net.lkrnac.book.eiws.chapter07;

import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
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
    postprocess(message);
    simpleService.processText(message);
  }

  private void postprocess(String message) {
    // simulate error
    if (!errorSimulated) {
      errorSimulated = true;
      throw new IllegalArgumentException(message);
    }
  }
}
