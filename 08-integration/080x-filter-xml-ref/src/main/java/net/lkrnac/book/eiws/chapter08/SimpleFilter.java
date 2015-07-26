package net.lkrnac.book.eiws.chapter08;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SimpleFilter implements MessageSelector {
  @Override
  public boolean accept(Message<?> message) {
    String stringPayload = (String) message.getPayload();
    return !stringPayload.contains("corrupt");
  }
}
