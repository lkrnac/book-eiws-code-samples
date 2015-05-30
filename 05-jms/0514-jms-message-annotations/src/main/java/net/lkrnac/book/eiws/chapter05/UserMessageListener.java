package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {
  private UserWithRoleService userService;

  @Autowired
  public UserMessageListener(UserWithRoleService userService) {
    super();
    this.userService = userService;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessage(@Payload User user, @Header String role) {
    userService.processUser(user, role);
  }
}
