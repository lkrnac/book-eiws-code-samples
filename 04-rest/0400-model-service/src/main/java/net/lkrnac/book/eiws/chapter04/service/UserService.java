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
  public static int SIMULATE_ERROR_ID = -1;

  @SuppressWarnings("PMD.UseConcurrentHashMap")
  private final Map<Integer, User> users = new HashMap<>();
  private int userSequence;

  public synchronized Collection<User> getAllUsers() {
    return Collections.unmodifiableCollection(users.values());
  }

  public synchronized User getUser(int identifier) {
    if (identifier == SIMULATE_ERROR_ID) {
      throw new UnsupportedOperationException("Identifier " + SIMULATE_ERROR_ID
          + " is not supported.");
    }
    return users.get(identifier);
  }

  public synchronized int addUser(User user) {
    users.put(userSequence, user);
    return userSequence++;
  }

  public synchronized void updateOrAddUser(int identifier, User user) {
    users.put(identifier, user);
  }

  public synchronized User deleteUser(int identifier) {
    return users.remove(identifier);
  }
}
