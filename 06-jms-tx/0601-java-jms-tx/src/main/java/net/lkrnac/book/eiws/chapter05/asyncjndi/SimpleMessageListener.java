package net.lkrnac.book.eiws.chapter05.asyncjndi;

import javax.jms.Message;
import javax.jms.MessageListener;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter05.text.SimpleService;

@Slf4j
public class SimpleMessageListener implements MessageListener {
  private SimpleService simpleService;

  public SimpleMessageListener(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  @Override
  public void onMessage(Message message) {
    try {
      simpleService.processText(message.getBody(String.class));
    } catch (Throwable t) {
      log.error("Error during message reception", t);
    }
  }
}
