package net.lkrnac.book.eiws.chapter08;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.lkrnac.book.eiws.chapter08.model.User;
import net.lkrnac.book.eiws.chapter08.out.UserRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class TestUserRepository extends UserRepository {
  private final BlockingQueue<User> queue = new ArrayBlockingQueue<>(10);

  @Override
  public int writeUser(User user) {
    queue.add(user);
    return 1;
  }

  public User getUser() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
