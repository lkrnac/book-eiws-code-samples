package net.lkrnac.book.eiws.chapter01.scheduling.task;

import org.springframework.stereotype.Component;

@Component
public class SimpleLogger {
  @SuppressWarnings("PMD.SystemPrintln")
  public void log(String message) {
    System.out.println(message);
  }
}
