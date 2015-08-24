package net.lkrnac.book.eiws.chapter08.out;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter08.model.User;

import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepository {
  public void persistUser(User user) {
    log.info("Object received: " + user);
  }
}
