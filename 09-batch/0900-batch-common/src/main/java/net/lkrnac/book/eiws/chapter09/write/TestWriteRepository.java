package net.lkrnac.book.eiws.chapter09.write;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Primary
@Profile("integration-test")
@Component
public class TestWriteRepository extends WriteRepository {
  private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(20);

  @Override
  public void writeRecords(List<String> message) {
    queue.addAll(message);
  }

  public String getMessage() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
