package net.lkrnac.book.eiws.chapter04.persistence;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import net.lkrnac.book.eiws.chapter04.model.User;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
public class UserRepository {
  @SuppressWarnings("PMD.UseConcurrentHashMap")
  private final Map<Integer, User> users = new HashMap<>();
  private int userSequence;

  public synchronized Collection<User> getAllUsers() {
    return Collections.unmodifiableCollection(users.values());
  }

  public synchronized User getUser(int identifier) {
    if (identifier == -1) {
      throw new UnsupportedOperationException("Identifier -1 is not supported.");
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

  @SuppressWarnings("PMD.ShortVariable")
  public synchronized Collection<User> getUsersInterval(int lowerId, int upperId) {
    //@formatter:off
    Collection<User> usersInIdInterval = users.entrySet().stream()
        .filter(p -> p.getKey() >= lowerId && p.getKey() <= upperId)
        .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()))
        .values();
    //@formatter:on
    return Collections.unmodifiableCollection(usersInIdInterval);
  }
}
