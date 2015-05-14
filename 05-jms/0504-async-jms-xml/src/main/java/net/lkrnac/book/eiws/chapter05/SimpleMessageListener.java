package net.lkrnac.book.eiws.chapter05;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SimpleMessageListener implements MessageListener {
  private SimpleMessageHandler simpleMessageHandler;

  public SimpleMessageListener(SimpleMessageHandler simpleMessageHandler) {
    super();
    this.simpleMessageHandler = simpleMessageHandler;
  }

  @Override
  public void onMessage(Message message) {

    try {
      TextMessage textMessage = (TextMessage) message;
      simpleMessageHandler.handleMessage(textMessage.getText());
    } catch (JMSException ex) {
      ex.printStackTrace();
    }
  }
}
