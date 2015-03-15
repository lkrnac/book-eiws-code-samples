package net.lkrnac.book.eiws.chapter01.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import net.lkrnac.book.eiws.chapter01.async.task.Caller;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan(basePackageClasses = Caller.class)
@EnableAsync
public class AsyncConfigurationSmallerPool {
  @Bean
  public Executor customTaskExecutor() {
    return Executors.newWorkStealingPool();
  }

  public static void main(String... args) throws InterruptedException {
    ApplicationContext context =
        SpringApplication.run(AsyncConfigurationSmallerPool.class, args);
    Caller caller = context.getBean(Caller.class);
    caller.kickOffAsyncTasks(10);
  }
}
