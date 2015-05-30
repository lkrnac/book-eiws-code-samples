package net.lkrnac.book.eiws.chapter05.test.simplemessage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.lkrnac.book.eiws.chapter05.SimpleService;

public class TestingSimpleService extends SimpleService {
  private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

  @Override
  public void processText(String text) {
    queue.add(text);
  }

  public String getMessage() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
