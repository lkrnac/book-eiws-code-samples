package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener {
  private SimpleService simpleService;

  @Autowired
  public SimpleMessageListener(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  public void readMessage(String message) throws JMSException {
    simpleService.processText(message);
  }

  public void readMessageLost(String message) throws JMSException {
    if ("simple message lost".equals(message)) {
      throw new IllegalArgumentException(message);
    }
    simpleService.processText(message);
  }

  public void readMessageDuplicate(String message) throws JMSException {
    simpleService.processText(message);
    if ("simple message duplicate".equals(message)) {
      throw new IllegalArgumentException(message);
    }
  }
}
