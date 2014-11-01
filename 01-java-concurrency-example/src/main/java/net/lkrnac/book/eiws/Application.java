package net.lkrnac.book.eiws;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Application {
	private static final int EXEC_COUNT = 10;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newWorkStealingPool();
		List<Future<String>> results = new ArrayList<>(10);

		for (int idx = 0; idx < EXEC_COUNT; idx++) {
			SimpleTask task = new SimpleTask();
			results.add(executorService.submit(task));
		}

		//behavior would be practically same also without these two lines
		executorService.shutdown();
		executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);

		for (Future<String> result : results) {
			try {
				System.out.println(result.get());
			} catch (InterruptedException | ExecutionException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
}
