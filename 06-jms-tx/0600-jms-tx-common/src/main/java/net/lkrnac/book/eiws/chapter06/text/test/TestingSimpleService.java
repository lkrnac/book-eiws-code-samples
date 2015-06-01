package net.lkrnac.book.eiws.chapter06.text.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.lkrnac.book.eiws.chapter06.text.SimpleService;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("integration-test")
@Primary
@Component
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
