package net.lkrnac.book.eiws.chapter05.userwithrole.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import lombok.Value;
import net.lkrnac.book.eiws.chapter05.user.User;
import net.lkrnac.book.eiws.chapter05.userwithrole.UserWithRoleService;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("integration-test")
@Primary
@Component
public class TestingUserWithRoleService extends UserWithRoleService {
  private final BlockingQueue<UserWithRoleTuple> queue =
      new ArrayBlockingQueue<>(10);

  @Value
  class UserWithRoleTuple {
    private User user;
    private String role;
  }

  @Override
  public void processUser(User user, String role) {
    queue.add(new UserWithRoleTuple(user, role));
  }

  public UserWithRoleTuple getUserWithRoleTuple() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
