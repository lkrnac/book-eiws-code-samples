package net.lkrnac.book.eiws.chapter08.in;

import org.springframework.messaging.Message;

public interface SiWrapperServiceMessage {
  public void processMessage(Message<String> message);
}
