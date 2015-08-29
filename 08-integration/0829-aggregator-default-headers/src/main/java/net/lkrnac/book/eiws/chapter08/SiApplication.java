package net.lkrnac.book.eiws.chapter08;

import java.util.concurrent.Executor;

import net.lkrnac.book.eiws.chapter08.in.AsyncMessageSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@SpringBootApplication
@IntegrationComponentScan
public class SiApplication {
  @Bean
  public Executor customExecutor() {
    ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
    threadPool.setCorePoolSize(10);
    return threadPool;
  }

  public static void main(String[] args) throws Exception {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    AsyncMessageSender messageSender = ctx.getBean(AsyncMessageSender.class);
    messageSender.sendMessage("message1", 1);
    messageSender.sendMessage("message2", 2);
    messageSender.sendMessage("message2", 2);
    messageSender.sendMessage("message1", 1);
  }
}
