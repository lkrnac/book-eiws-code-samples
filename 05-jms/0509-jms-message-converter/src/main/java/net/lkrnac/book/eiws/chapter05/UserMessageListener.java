package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {
  private UserService userService;

  @Autowired
  public UserMessageListener(UserService userService) {
    super();
    this.userService = userService;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessage(User user) {
    userService.processUser(user);
  }
}
