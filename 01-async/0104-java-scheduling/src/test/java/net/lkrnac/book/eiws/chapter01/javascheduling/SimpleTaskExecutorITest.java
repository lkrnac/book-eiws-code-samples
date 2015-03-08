package net.lkrnac.book.eiws.chapter01.javascheduling;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.lkrnac.book.eiws.chapter01.javascheduling.SimpleLogger;
import net.lkrnac.book.eiws.chapter01.javascheduling.SimpleTaskExecutor;

import org.testng.annotations.Test;

@SuppressWarnings("PMD.MethodNamingConventions")
public class SimpleTaskExecutorITest {
  private static final int TEST_TIMEOUT = 4000;
  private static final int EXEC_COUNT = 10;

  @Test(timeOut = TEST_TIMEOUT)
  public final void testExecuteTasks_threadsCountEqualTasksCount()
      throws InterruptedException {
    // GIVEN
    ExecutorService executorService = Executors.newFixedThreadPool(EXEC_COUNT);

    testSimpleTaskExecutor(executorService);
  }

  @Test(timeOut = TEST_TIMEOUT)
  public void testExecuteTasks_threadsCountLessThanTasksCount()
      throws InterruptedException {
    // GIVEN
    ExecutorService executorService = Executors.newWorkStealingPool();

    testSimpleTaskExecutor(executorService);
  }

  private void testSimpleTaskExecutor(ExecutorService executorService)
      throws InterruptedException {
    SimpleLogger logger = mock(SimpleLogger.class);
    SimpleTaskExecutor simpleTaskExecutor = new SimpleTaskExecutor(logger,
        executorService, EXEC_COUNT);

    // WHEN
    simpleTaskExecutor.executeTasks();

    // THEN
    verify(logger, times(EXEC_COUNT + 1)).log(anyString());
  }
}
