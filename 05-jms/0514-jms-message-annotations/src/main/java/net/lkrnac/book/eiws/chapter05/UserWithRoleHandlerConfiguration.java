package net.lkrnac.book.eiws.chapter05;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class UserWithRoleHandlerConfiguration {
  @Bean
  public UserWithRoleHandler userHandler() {
    return new UserWithRoleHandler() {
      @Override
      public void handleUser(User user, String role) {
        log.info("User object Received: {} with role {}", user, role);
      }
    };
  }
}
