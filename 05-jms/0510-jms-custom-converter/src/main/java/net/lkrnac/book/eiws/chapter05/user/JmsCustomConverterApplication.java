package net.lkrnac.book.eiws.chapter05.user;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter05.user.User;
import net.lkrnac.book.eiws.chapter05.user.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class JmsCustomConverterApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsCustomConverterApplication.class, args);
  }

  @Bean
  public UserService userService() {
    return new UserService() {
      @Override
      public void processUser(User user) {
        log.info("User object Received: {}", user);
      }
    };
  }
}
