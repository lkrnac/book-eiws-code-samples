package net.lkrnac.book.eiws.chapter05.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestingMessageHandler implements SimpleMessageHandler {
  private final BlockingQueue queue = new ArrayBlockingQueue<>(10);
  private String message;

  @Override
  public void handleMessage(String message) {
    this.message = message;
    queue.add(message);
  }

  public String getMessage() {
    try {
      return (String) queue.take();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
}
