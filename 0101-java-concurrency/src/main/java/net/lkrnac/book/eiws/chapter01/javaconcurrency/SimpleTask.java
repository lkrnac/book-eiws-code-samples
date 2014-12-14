package net.lkrnac.book.eiws.chapter01.javaconcurrency;

import java.util.Random;
import java.util.concurrent.Callable;

public class SimpleTask implements Callable<String> {
  private static final int SIMULATE_IO = 1000;
  private final Random random = new Random();

  @Override
  @SuppressWarnings("PMD.SignatureDeclareThrowsException")
  public String call() throws Exception {
    Integer value = random.nextInt();
    String threadName = Thread.currentThread().getName();

    Thread.sleep(SIMULATE_IO);
    if (value % 2 == 0) {
      throw new UnsupportedOperationException(threadName + " threw exception");
    }
    return threadName + " generated number " + value;
  }
}
