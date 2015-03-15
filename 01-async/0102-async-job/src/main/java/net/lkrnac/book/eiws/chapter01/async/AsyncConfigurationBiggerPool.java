package net.lkrnac.book.eiws.chapter01.async;

import java.util.concurrent.Executor;

import net.lkrnac.book.eiws.chapter01.async.task.Caller;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan(basePackageClasses = Caller.class)
@EnableAsync
public class AsyncConfigurationBiggerPool {
  private static final int EXEC_COUNT = 10;

  @Bean
  public Executor customTaskExecutor() {
    ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
    threadPool.setCorePoolSize(EXEC_COUNT);
    return threadPool;
  }

  public static void main(String... args) throws InterruptedException {
    ApplicationContext context =
        SpringApplication.run(AsyncConfigurationBiggerPool.class, args);
    Caller caller = context.getBean(Caller.class);
    caller.kickOffAsyncTasks(EXEC_COUNT);
  }
}
