package net.lkrnac.book.eiws.chapter08;

import org.springframework.integration.handler.LoggingHandler;

public class TestLoggingHandler extends LoggingHandler {
  public TestLoggingHandler() {
    super("INFO");
  }
}
