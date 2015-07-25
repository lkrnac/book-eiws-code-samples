package net.lkrnac.book.eiws.chapter08;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class TestWriteRepository extends WriteRepository {
  private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

  @Override
  public void write(String message) {
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
