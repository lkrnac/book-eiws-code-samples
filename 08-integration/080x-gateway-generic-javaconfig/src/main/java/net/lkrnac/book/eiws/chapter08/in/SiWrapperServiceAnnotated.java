package net.lkrnac.book.eiws.chapter08.in;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SiWrapperServiceAnnotated {
  @Gateway(requestChannel = "gatewayChannel")
  public boolean processText(String text);
}
