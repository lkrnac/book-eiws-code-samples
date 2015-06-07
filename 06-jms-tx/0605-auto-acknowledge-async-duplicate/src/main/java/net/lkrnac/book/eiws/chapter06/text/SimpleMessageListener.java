package net.lkrnac.book.eiws.chapter06.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener {
  private CompositeService compositeService;
  private boolean errorSimulated = false;

  @Autowired
  public SimpleMessageListener(CompositeService compositeService) {
    super();
    this.compositeService = compositeService;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessage(String message) {
    compositeService.processText(message);
    postprocess(message);
  }

  private void postprocess(String message) {
    // simulate error
    if (!errorSimulated) {
      errorSimulated = true;
      throw new IllegalArgumentException(message);
    }
  }

}
