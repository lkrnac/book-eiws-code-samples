package net.lkrnac.book.eiws.chapter05;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@Configuration
@ComponentScan
@EnableScheduling
@ImportResource("classpath:spring-jms.xml")
public class JmsNamespaceAsyncApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsNamespaceAsyncApplication.class, args);
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
