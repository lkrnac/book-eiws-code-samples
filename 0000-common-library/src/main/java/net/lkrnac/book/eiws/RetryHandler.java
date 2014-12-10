package net.lkrnac.book.eiws;

import java.util.function.Function;

public class RetryHandler<T, R> {
  public R retry(final Function<T, R> function, T parameter, final int timeout) {
    final long start = System.currentTimeMillis();
    R result = null;
    while ((System.currentTimeMillis() - start) < timeout) {
      try {
        Thread.sleep(50);
        return function.apply(parameter);
      } catch (Throwable throwable) {
      }
    }
    return result;
  }
}
