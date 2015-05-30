package net.lkrnac.book.eiws.chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
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
  public void readMessage(Message<User> userMessage) {
    String role = userMessage.getHeaders().get("role", String.class);
    userHandler.processUser(userMessage.getPayload(), role);
  }
}
