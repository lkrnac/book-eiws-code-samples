package net.lkrnac.book.eiws.chapter05.userwithrole;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter05.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserMessageListener {
  private UserWithRoleService userWithRoleService;

  @Autowired
  public UserMessageListener(UserWithRoleService userWithRoleService) {
    super();
    this.userWithRoleService = userWithRoleService;
  }

  @JmsListener(destination = "queue/ExpiryQueue")
  public void readMessage(@Payload User user, @Header String role,
      @Headers Map<String, Object> headers) {
    log.info("Message with ID " + headers.get("id") + " received");
    userWithRoleService.processUser(user, role);
  }
}
