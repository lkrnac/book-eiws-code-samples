package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener {
  private SimpleService simpleService;

  @Autowired
  public SimpleMessageListener(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessage(String message) throws JMSException {
    simpleService.processText(message);
  }

  @JmsListener(destination = "messageQueueCorrupted")
  public void readMessageCorrupted(String message) throws JMSException {
    if ("simple message corrupted".equals(message)) {
      throw new IllegalArgumentException(message);
    }
    simpleService.processText(message);
  }

  @JmsListener(destination = "messageQueueDuplicate")
  public void readMessageDuplicate(String message) throws JMSException {
    simpleService.processText(message);
    if ("simple message duplicate".equals(message)) {
      throw new IllegalArgumentException(message);
    }
  }

}
