package net.lkrnac.book.eiws.chapter05.user;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
  public void processUser(User user) {
    log.info("User object Received: {}", user);
  }
}
