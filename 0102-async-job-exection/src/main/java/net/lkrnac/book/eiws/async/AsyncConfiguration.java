package net.lkrnac.book.eiws.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan
@EnableAsync
public class AsyncConfiguration {
	@Bean
	@Qualifier("customTaskExecutor")
	public ExecutorService createThreadPool(){
		return Executors.newWorkStealingPool();
		//return Executors.newWorkStealingPool(10);
	}
}
