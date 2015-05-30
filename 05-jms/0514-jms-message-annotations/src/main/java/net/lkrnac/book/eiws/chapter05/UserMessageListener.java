package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {
  private UserWithRoleService userHandler;

  @Autowired
  public UserMessageListener(UserWithRoleService userHandler) {
    super();
    this.userHandler = userHandler;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessage(@Payload User user, @Header String role) {
    userHandler.processUser(user, role);
  }
}
