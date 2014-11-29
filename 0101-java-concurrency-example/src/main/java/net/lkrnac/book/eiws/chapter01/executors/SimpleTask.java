package net.lkrnac.book.eiws.chapter01.executors;

import java.util.Random;
import java.util.concurrent.Callable;

public class SimpleTask implements Callable<String>{
	@Override
	public String call() throws Exception {
		Integer value = new Random().nextInt();
		String threadName = Thread.currentThread().getName();

		Thread.sleep(1000);
		if (value % 2 == 0){
			throw new UnsupportedOperationException(threadName + " threw exception");
		}
		return threadName + " generated number " + value;
	}
}
