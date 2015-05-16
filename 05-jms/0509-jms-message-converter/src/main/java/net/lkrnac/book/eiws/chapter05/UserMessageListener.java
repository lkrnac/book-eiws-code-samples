package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {
  private UserHandler userHandler;

  @Autowired
  public UserMessageListener(UserHandler userHandler) {
    super();
    this.userHandler = userHandler;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessage(User user) {
    userHandler.handleUser(user);
  }
}
