package net.lkrnac.book.eiws.chapter08;

import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class SimpleFilter {
  @Filter(inputChannel = "inChannel", outputChannel = "filteredChannel")
  public boolean accept(String message) {
    return !message.contains("corrupt");
  }
}
