package net.lkrnac.book.eiws.chapter01.javaconcurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class SimpleTaskExecutor {
  private final SimpleLogger logger;
  private final ExecutorService executorService;
  private final int execCount;

  /**
   * Creates {@link SimpleTaskExecutor} instance.
   * 
   * @param simpleLogger
   *          logger
   * @param executorService
   *          thread pool
   * @param execCount
   *          simple task execution count
   */
  public SimpleTaskExecutor(SimpleLogger simpleLogger,
      ExecutorService executorService, int execCount) {
    super();
    this.logger = simpleLogger;
    this.executorService = executorService;
    this.execCount = execCount;
  }

  /**
   * 
   * @throws InterruptedException
   */
  public void executeTasks() throws InterruptedException {
    Collection<Future<String>> results = new ArrayList<>(execCount);

    long start = System.currentTimeMillis(); //
    SimpleTask task = new SimpleTask();
    for (int idx = 0; idx < execCount; idx++) {
      results.add(executorService.submit(task));
    }

    // behavior would be practically same also without these two lines
    executorService.shutdown();
    executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);

    results.forEach(result -> {
      try {
        logger.log(result.get());
      } catch (InterruptedException | ExecutionException exception) {
        logger.log(exception.getLocalizedMessage());
      }
    });
    logger.log("Elapsed time: " + (System.currentTimeMillis() - start));
  }

}
