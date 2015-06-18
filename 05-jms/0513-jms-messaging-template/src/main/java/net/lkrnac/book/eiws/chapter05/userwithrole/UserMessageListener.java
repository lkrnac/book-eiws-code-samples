package net.lkrnac.book.eiws.chapter05.userwithrole;

import net.lkrnac.book.eiws.chapter05.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
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
  public void readMessage(Message<User> userMessage) {
    String role = userMessage.getHeaders().get("role", String.class);
    userWithRoleService.processUser(userMessage.getPayload(), role);
  }
}
