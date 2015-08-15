package net.lkrnac.book.eiws.chapter08;

import org.springframework.messaging.Message;

public interface SimpleService {
  public void processMessage(Message<String> message);
}
