package net.lkrnac.book.eiws.chapter05;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SimpleMessageListener implements MessageListener {
  @Override
  public void onMessage(Message message) {
    try {
      TextMessage textMessage = (TextMessage) message;
      System.out.println(textMessage.getText());
    } catch (JMSException ex) {
      ex.printStackTrace();
    }
  }
}
