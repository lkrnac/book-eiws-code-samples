package net.lkrnac.book.eiws.chapter05.test.simplemessage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.lkrnac.book.eiws.chapter05.SimpleMessageHandler;

public class TestingSimpleMessageHandler implements SimpleMessageHandler {
  private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

  @Override
  public void handleMessage(String message) {
    queue.add(message);
  }

  public String getMessage() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
