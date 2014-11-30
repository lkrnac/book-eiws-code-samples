package net.lkrnac.book.eiws.chapter01.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.lkrnac.book.eiws.chapter01.async.task.Caller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan(SpringConstants.TASK_PACKAGE)
@EnableAsync
public class AsyncConfigurationSmallerPool {
  private static final int EXEC_COUNT = 10;

  @Bean
  @Qualifier(SpringConstants.TASK_EXECUTOR)
  public ExecutorService createThreadPool() {
    return Executors.newWorkStealingPool();
  }

  public static void main(String[] args) throws InterruptedException {
    ApplicationContext context = SpringApplication.run(
        AsyncConfigurationBiggerPool.class, args);
    Caller caller = context.getBean(Caller.class);
    caller.kickOffAsyncTasks(EXEC_COUNT);
  }
}
