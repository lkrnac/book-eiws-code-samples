package net.lkrnac.book.eiws.chapter05;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class JmsMessagingTemplateAsyncApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsMessagingTemplateAsyncApplication.class, args);
  }

  @Bean
  public SimpleMessageHandler simpleMessageHandler() {
    return new SimpleMessageHandler() {
      @Override
      public void handleMessage(String message) {
        log.info("Message Received: {}", message);
      }
    };
  }
}
