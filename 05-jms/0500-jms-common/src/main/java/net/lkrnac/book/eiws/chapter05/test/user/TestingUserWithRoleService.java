package net.lkrnac.book.eiws.chapter05.test.user;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import lombok.Value;
import net.lkrnac.book.eiws.chapter05.User;
import net.lkrnac.book.eiws.chapter05.UserWithRoleService;

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
