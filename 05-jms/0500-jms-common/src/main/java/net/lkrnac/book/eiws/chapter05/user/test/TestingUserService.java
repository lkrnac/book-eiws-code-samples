package net.lkrnac.book.eiws.chapter05.user.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.lkrnac.book.eiws.chapter05.user.User;
import net.lkrnac.book.eiws.chapter05.user.UserService;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("integration-test")
@Primary
@Component
public class TestingUserService extends UserService {
  private final BlockingQueue<User> queue = new ArrayBlockingQueue<>(10);

  @Override
  public void processUser(User user) {
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
