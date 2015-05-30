package net.lkrnac.book.eiws.chapter05;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserWithRoleService {
  public void processUser(User user, String role) {
    log.info("User object Received: {} with role {}", user, role);
  }
}
