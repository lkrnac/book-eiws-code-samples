package net.lkrnac.book.eiws.chapter01.executors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Application {
	private static final int EXEC_COUNT = 10;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newWorkStealingPool(EXEC_COUNT);
		Collection<Future<String>> results = new ArrayList<>(EXEC_COUNT);

		long start = System.currentTimeMillis();
		for (int idx = 0; idx < EXEC_COUNT; idx++) {
			SimpleTask task = new SimpleTask();
			results.add(executorService.submit(task));
		}

		//behavior would be practically same also without these two lines
		executorService.shutdown();
		executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);

		results.forEach(result -> {
			try {
				System.out.println(result.get());
			} catch (InterruptedException | ExecutionException exception) {
				System.out.println(exception.getLocalizedMessage());
			}
		});
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
	}
}
