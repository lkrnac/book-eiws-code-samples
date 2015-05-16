package net.lkrnac.book.eiws.chapter05.test.user;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.lkrnac.book.eiws.chapter05.User;
import net.lkrnac.book.eiws.chapter05.UserHandler;

public class TestingUserHandler implements UserHandler {
  private final BlockingQueue<User> queue = new ArrayBlockingQueue<>(10);

  @Override
  public void handleUser(User user) {
    queue.add(user);
  }

  public User getUser() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
