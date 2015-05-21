package net.lkrnac.book.eiws.chapter05.asyncjndi;

import javax.jms.Message;
import javax.jms.MessageListener;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter05.SimpleMessageHandler;

@Slf4j
public class SimpleMessageListener implements MessageListener {
  private SimpleMessageHandler simpleMessageHandler;

  public SimpleMessageListener(SimpleMessageHandler simpleMessageHandler) {
    super();
    this.simpleMessageHandler = simpleMessageHandler;
  }

  @Override
  public void onMessage(Message message) {
    try {
      simpleMessageHandler.handleMessage(message.getBody(String.class));
    } catch (Throwable t) {
      log.error("Error during message reception", t);
    }
  }
}
