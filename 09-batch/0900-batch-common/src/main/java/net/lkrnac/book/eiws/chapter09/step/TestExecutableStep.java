package net.lkrnac.book.eiws.chapter09.step;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Primary
@Profile("integration-test")
@Component
public class TestExecutableStep extends SimpleExecutableStep {
  private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(20);

  @Override
  public void executeStep(String stepName) {
    queue.add(stepName);
  }

  public String getMessage() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
