package net.lkrnac.book.eiws.chapter08.out;

import net.lkrnac.book.eiws.chapter08.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    super();
    this.userRepository = userRepository;
  }

  public void processUser(User user) {
    userRepository.persistUser(user);
  }
}
