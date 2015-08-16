package net.lkrnac.book.eiws.chapter08.out;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter08.model.User;

import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepository {
  public int writeUser(User user) {
    log.info(user.toString());
    return 1;
  }
}
