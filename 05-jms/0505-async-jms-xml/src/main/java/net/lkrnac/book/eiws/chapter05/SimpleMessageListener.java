package net.lkrnac.book.eiws.chapter05;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener implements MessageListener {
  private SimpleService simpleMessageHandler;

  @Autowired
  public SimpleMessageListener(SimpleService simpleMessageHandler) {
    super();
    this.simpleMessageHandler = simpleMessageHandler;
  }

  @Override
  public void onMessage(Message message) {
    try {
      TextMessage textMessage = (TextMessage) message;
      simpleMessageHandler.processText(textMessage.getText());
    } catch (JMSException ex) {
      ex.printStackTrace();
    }
  }
}
