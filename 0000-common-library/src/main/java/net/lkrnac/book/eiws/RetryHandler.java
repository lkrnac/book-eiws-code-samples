package net.lkrnac.book.eiws;

import java.util.function.Function;

/**
 * Class responsible to retrying operations.
 * 
 * @author Lubos Krnac
 *
 * @param <T>
 *          parameter for retry operation (function)
 * @param <R>
 *          return type of retry operation (function)
 */
public class RetryHandler<T, R> {
  private static final int RETRY_TIMEOUT = 50;

  /**
   * Tries to execute given function in loop and ignore errors. Returns after
   * first successful call.
   * 
   * @param function
   *          method to execute
   * @param parameter
   *          parameter to pass into method
   * @param timeout
   *          timeout of retry loop
   * @return return value from function
   */
  @SuppressWarnings({ "checkstyle:emptyblock", "PMD.AvoidCatchingThrowable",
      "PMD.EmptyCatchBlock" })
  public final R retry(Function<T, R> function, T parameter, int timeout) {
    long start = System.currentTimeMillis();
    while ((System.currentTimeMillis() - start) < timeout) {
      try {
        Thread.sleep(RETRY_TIMEOUT);
        return function.apply(parameter);
      } catch (Throwable throwable) {
        // ignore errors
      }
    }
    return null;
  }
}
