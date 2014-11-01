package net.lkrnac.book.eiws.async;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
class AsyncTask {
	@Async("customTaskExecutor")
	public Future<String> call(int parameter) throws InterruptedException {
		String threadName = Thread.currentThread().getName();

		Thread.sleep(2000);
		if (parameter % 2 == 0) {
			throw new UnsupportedOperationException(threadName
					+ " threw exception");
		}
		return new AsyncResult<String>(threadName + " have parameter "
				+ parameter);
	}
}
