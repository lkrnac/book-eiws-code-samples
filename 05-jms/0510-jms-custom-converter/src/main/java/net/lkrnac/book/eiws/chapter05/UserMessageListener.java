package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {
  private UserService userHandler;

  @Autowired
  public UserMessageListener(UserService userHandler) {
    super();
    this.userHandler = userHandler;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessage(User user) {
    userHandler.processUser(user);
  }
}
