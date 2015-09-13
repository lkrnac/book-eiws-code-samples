package net.lkrnac.book.eiws.chapter09.step;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Primary
@Profile("integration-test")
@Component
public class TestExecutablePoint extends SimpleExecutablePoint {
  private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

  @Override
  public void execute(String message) {
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
