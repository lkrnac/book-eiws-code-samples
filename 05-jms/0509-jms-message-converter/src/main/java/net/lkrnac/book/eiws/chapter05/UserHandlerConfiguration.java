package net.lkrnac.book.eiws.chapter05;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class UserHandlerConfiguration {
  @Bean
  public UserHandler userHandler() {
    return new UserHandler() {
      @Override
      public void handleUser(User user) {
        log.info("User object Received: {}", user);
      }
    };
  }
}
