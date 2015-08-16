package net.lkrnac.book.eiws.chapter08.in;

import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SiWrapperServiceFutureAnnotated {
  @Gateway(requestChannel = "inChannel")
  Future<Boolean> processText(String text);
}
