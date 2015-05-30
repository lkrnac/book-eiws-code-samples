package net.lkrnac.book.eiws.chapter05.text;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter05.text.SimpleService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@Configuration
@EnableScheduling
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
