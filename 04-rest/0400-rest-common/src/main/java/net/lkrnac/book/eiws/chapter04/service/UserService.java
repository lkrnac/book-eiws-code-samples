package net.lkrnac.book.eiws.chapter04.service;

import java.util.Collection;

import net.lkrnac.book.eiws.chapter04.model.User;
import net.lkrnac.book.eiws.chapter04.persistence.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    super();
    this.userRepository = userRepository;
  }

  public synchronized Collection<User> getAllUsers() {
    return userRepository.getAllUsers();
  }

  public synchronized User getUser(int identifier) {
    return userRepository.getUser(identifier);
  }

  public synchronized int addUser(User user) {
    return userRepository.addUser(user);
  }

  public synchronized void updateOrAddUser(int identifier, User user) {
    userRepository.updateOrAddUser(identifier, user);
  }

  public synchronized User deleteUser(int identifier) {
    return userRepository.deleteUser(identifier);
  }
}
