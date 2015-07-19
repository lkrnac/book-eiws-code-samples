package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SimpleMessageListener {
  private SimpleService simpleService;

  @Autowired
  public SimpleMessageListener(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  @Transactional
  @JmsListener(destination = "ExpiryQueue")
  public void readMessage(String messageText, Message message)
      throws JMSException {
    if (message.getJMSRedelivered()) {
      processIfNeeded(messageText);
    } else {
      preprocess(messageText);
      simpleService.processText(messageText);
    }
  }

  private void processIfNeeded(String messageText) {
    if (!simpleService.isProcessed(messageText)) {
      simpleService.processText(messageText);
    }
  }

  private void preprocess(String messageText) {
    // simulate error
    throw new IllegalArgumentException(messageText);
  }
}
