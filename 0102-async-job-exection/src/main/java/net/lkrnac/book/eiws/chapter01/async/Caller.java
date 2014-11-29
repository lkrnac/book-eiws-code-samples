package net.lkrnac.book.eiws.chapter01.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Caller {
	private static final int EXEC_COUNT = 10;
	private AsyncTask asyncTask;

	@Autowired
	public Caller(AsyncTask asyncTask) {
		this.asyncTask = asyncTask;
	}

	@PostConstruct
	public void kickOffAsyncTasks() throws InterruptedException {
		Collection<Future<String>> results = new ArrayList<>(EXEC_COUNT);

		for (int idx = 0; idx < EXEC_COUNT; idx++) {
			results.add(asyncTask.call(idx));
		}

		results.forEach(result -> {
			try {
				System.out.println(result.get());
			} catch (InterruptedException | ExecutionException e) {
				System.out.println(e.getLocalizedMessage());
			}
		});
	}
}
