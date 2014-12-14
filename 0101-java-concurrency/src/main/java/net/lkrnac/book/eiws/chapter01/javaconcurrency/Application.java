package net.lkrnac.book.eiws.chapter01.javaconcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Application {
  private static final int EXEC_COUNT = 10;

  private Application() {
  }

  public static void main(final String... args) throws InterruptedException {
    SimpleLogger simpleLogger = new SimpleLogger();

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(EXEC_COUNT);
    SimpleTaskExecutor executorFast = new SimpleTaskExecutor(simpleLogger,
        fixedThreadPool, EXEC_COUNT);
    executorFast.executeTasks();

    ExecutorService workStealingPool = Executors.newWorkStealingPool();
    SimpleTaskExecutor executorSlow = new SimpleTaskExecutor(simpleLogger,
        workStealingPool, EXEC_COUNT);
    executorSlow.executeTasks();
  }
}
