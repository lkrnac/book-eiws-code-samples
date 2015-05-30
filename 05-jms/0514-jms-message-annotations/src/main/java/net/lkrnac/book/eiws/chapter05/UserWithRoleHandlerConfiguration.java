package net.lkrnac.book.eiws.chapter05;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class UserWithRoleHandlerConfiguration {
  @Bean
  public UserWithRoleService userService() {
    return new UserWithRoleService() {
      @Override
      public void processUser(User user, String role) {
        log.info("User object Received: {} with role {}", user, role);
      }
    };
  }
}
