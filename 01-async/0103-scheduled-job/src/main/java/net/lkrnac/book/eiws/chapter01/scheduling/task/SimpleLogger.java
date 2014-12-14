package net.lkrnac.book.eiws.chapter01.scheduling.task;

import org.springframework.stereotype.Component;

@Component
public class SimpleLogger {
  public void log(String message) {
    System.out.println(message);
  }
}
