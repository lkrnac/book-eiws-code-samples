package net.lkrnac.book.eiws.chapter01.async.task;

import org.springframework.stereotype.Component;

@Component
public class SimpleLogger {
  @SuppressWarnings("PMD.SystemPrintln")
  public void log(String message) {
    System.out.println(message);
  }
}
