package net.lkrnac.book.eiws.chapter08;

import org.springframework.messaging.Message;

public interface SiWrapperServiceWithHeaders {
  public void processMessage(Message<String> message);
}
