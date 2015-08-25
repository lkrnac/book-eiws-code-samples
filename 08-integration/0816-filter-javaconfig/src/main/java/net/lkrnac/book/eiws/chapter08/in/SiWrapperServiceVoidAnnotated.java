package net.lkrnac.book.eiws.chapter08.in;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SiWrapperServiceVoidAnnotated {
  @Gateway(requestChannel = "inChannel")
  public void processText(String text);
}
