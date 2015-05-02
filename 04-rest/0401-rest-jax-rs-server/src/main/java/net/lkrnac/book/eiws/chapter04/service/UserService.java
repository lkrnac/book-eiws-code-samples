package net.lkrnac.book.eiws.chapter04.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.lkrnac.book.eiws.chapter04.model.User;

import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
public class UserService {
  @SuppressWarnings("PMD.UseConcurrentHashMap")
  private final Map<Integer, User> users = new HashMap<>();
  private int userSequence;

  public synchronized Collection<User> getAllUsers() {
    return Collections.unmodifiableCollection(users.values());
  }

  public synchronized User getUser(int identifier) {
    return users.get(identifier);
  }

  public synchronized int addUser(User user) {
    int identifier = userSequence;
    userSequence++;
    user.setIdentifier(identifier);
    users.put(identifier, user);
    return identifier;
  }

  public synchronized User deleteUser(int identifier) {
    return users.remove(identifier);
  }
}
