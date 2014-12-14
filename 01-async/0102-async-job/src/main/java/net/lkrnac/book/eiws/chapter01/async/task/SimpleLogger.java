package net.lkrnac.book.eiws.chapter01.async.task;

import org.springframework.stereotype.Component;

@Component
public class SimpleLogger {
  public void log(String message) {
    System.out.println(message);
  }
}
