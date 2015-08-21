package net.lkrnac.book.eiws.chapter08;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.lkrnac.book.eiws.chapter08.out.WriteRepositoryWithHeaders;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("integration-test")
@Primary
@Component
public class TestWriteRepositoryWithHeaders extends WriteRepositoryWithHeaders {
  private final BlockingQueue<String[]> queue = new ArrayBlockingQueue<>(10);

  @Override
  public int writeWithHeader(String message, String simpleHeader) {
    queue.add(new String[] { message, simpleHeader });
    return 1;
  }

  public String[] getMessage() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
