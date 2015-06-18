package net.lkrnac.book.eiws.chapter05.userwithrole;

import net.lkrnac.book.eiws.chapter05.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {
  private UserWithRoleService userWithRoleService;

  @Autowired
  public UserMessageListener(UserWithRoleService userWithRoleService) {
    super();
    this.userWithRoleService = userWithRoleService;
  }

  @JmsListener(destination = "queue/ExpiryQueue")
  public void readMessage(@Payload User user, @Header String role) {
    userWithRoleService.processUser(user, role);
  }
}
