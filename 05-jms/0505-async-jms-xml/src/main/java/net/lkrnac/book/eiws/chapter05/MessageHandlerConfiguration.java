package net.lkrnac.book.eiws.chapter05;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MessageHandlerConfiguration {
  @Bean
  public SimpleService simpleService() {
    return new SimpleService() {
      @Override
      public void processText(String message) {
        log.info("Message Received: {}", message);
      }
    };
  }
}
