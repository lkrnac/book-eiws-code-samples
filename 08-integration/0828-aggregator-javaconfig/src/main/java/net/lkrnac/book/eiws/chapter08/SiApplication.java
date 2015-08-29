package net.lkrnac.book.eiws.chapter08;

import java.util.concurrent.Executor;

import net.lkrnac.book.eiws.chapter08.in.AsyncMessageSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@SpringBootApplication
public class SiApplication {
  private static final int EXEC_COUNT = 10;

  @Bean
  public Executor customTaskExecutor() {
    ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
    threadPool.setCorePoolSize(EXEC_COUNT);
    return threadPool;
  }

  public static void main(String[] args) throws Exception {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    AsyncMessageSender messageSender = ctx.getBean(AsyncMessageSender.class);
    messageSender.sendMessage("message1");
    messageSender.sendMessage("message2");
    messageSender.sendMessage("message2");
    messageSender.sendMessage("message1");
  }
}
