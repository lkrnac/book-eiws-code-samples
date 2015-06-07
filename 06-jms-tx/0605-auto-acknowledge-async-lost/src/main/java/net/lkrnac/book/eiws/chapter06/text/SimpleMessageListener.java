package net.lkrnac.book.eiws.chapter06.text;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleMessageListener {
  private SimpleService simpleService;

  @Autowired
  public SimpleMessageListener(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessageLost(String message) {
    try {
      preprocess(message);

      simpleService.processText(message);
    } catch (Throwable throwable) {
      log.error("Error occured: ", throwable);
    }
  }

  private void preprocess(String message) {
    // simulate error
    if ("simple message".equals(message)) {
      throw new IllegalArgumentException(message);
    }
  }
}
