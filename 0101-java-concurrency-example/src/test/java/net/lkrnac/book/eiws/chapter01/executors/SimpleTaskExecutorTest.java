package net.lkrnac.book.eiws.chapter01.executors;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.testng.annotations.Test;

public class SimpleTaskExecutorTest {
	private static final int EXEC_COUNT = 10;

	@Test(timeOut = 2000)
	public void testExecuteTasks_threadsCountEqualTasksCount() throws InterruptedException {
		// GIVEN
		ExecutorService executorService = Executors.newFixedThreadPool(EXEC_COUNT);

		testSimpleTaskExecutor(executorService);
	}

	@Test(timeOut = 8000)
	public void testExecuteTasks_threadsCountLessThanTasksCount() throws InterruptedException {
		// GIVEN
		ExecutorService executorService = Executors.newWorkStealingPool();

		testSimpleTaskExecutor(executorService);
	}

	private void testSimpleTaskExecutor(ExecutorService executorService) throws InterruptedException {
		SimpleLogger logger = mock(SimpleLogger.class);
		SimpleTaskExecutor simpleTaskExecutor = new SimpleTaskExecutor(logger, executorService, EXEC_COUNT);

		// WHEN
		simpleTaskExecutor.executeTasks();

		// THEN
		verify(logger, times(EXEC_COUNT + 1)).log(anyString());
	}
}
