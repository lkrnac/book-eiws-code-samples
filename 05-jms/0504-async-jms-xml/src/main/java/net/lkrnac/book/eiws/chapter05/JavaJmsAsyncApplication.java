package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@ComponentScan
@ImportResource("classpath:spring-jms.xml")
@EnableScheduling
public class JavaJmsAsyncApplication {
  @Bean
  public SimpleMessageHandler simpleMessageHandler() {
    return new SimpleMessageHandler() {
      @Override
      public void handleMessage(String message) {
        System.out.println(message);
      }
    };
  }

  @Autowired
  private SimpleMessageSender messageSender;

  @Scheduled(initialDelay = 1000, fixedRate = 1000L)
  public void sendMessage() throws InterruptedException {
    messageSender.send("simple message");
  }

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JavaJmsAsyncApplication.class, args);
  }
}
