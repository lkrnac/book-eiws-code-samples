package net.lkrnac.book.eiws.chapter08.in;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SiWrapperServiceAnnoated {
  @Gateway(requestChannel = "inChannel")
  public boolean processText(String text);
}
