package net.lkrnac.book.eiws.chapter08;

import org.springframework.stereotype.Component;

@Component
public class SimpleHeaderEnricher {
  public String addHeader(String message) {
    return message.contains("1") ? "header1" : "header2";
  }
}
