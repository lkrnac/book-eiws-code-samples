package net.lkrnac.book.eiws.chapter01.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

	private static final int EXEC_COUNT = 10;

	public static void main(String[] args) throws InterruptedException {
		SimpleLogger simpleLogger = new SimpleLogger();

		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(EXEC_COUNT);
		new SimpleTaskExecutor(simpleLogger, fixedThreadPool, EXEC_COUNT).executeTasks();

		ExecutorService workStealingPool = Executors.newWorkStealingPool();
		new SimpleTaskExecutor(simpleLogger, workStealingPool, EXEC_COUNT).executeTasks();
	}
}
