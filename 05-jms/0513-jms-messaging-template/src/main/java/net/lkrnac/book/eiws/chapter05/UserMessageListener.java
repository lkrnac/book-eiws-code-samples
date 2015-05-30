package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
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
  public void readMessage(Message<User> userMessage) {
    String role = userMessage.getHeaders().get("role", String.class);
    userService.processUser(userMessage.getPayload(), role);
  }
}
