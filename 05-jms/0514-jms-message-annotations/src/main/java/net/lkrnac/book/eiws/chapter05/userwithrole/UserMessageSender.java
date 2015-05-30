package net.lkrnac.book.eiws.chapter05.userwithrole;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter05.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserMessageSender {
  private static final String ADMIN = "admin";
  private JmsTemplate jmsTemplate;

  @Autowired
  public UserMessageSender(JmsTemplate jmsTemplate) {
    super();
    this.jmsTemplate = jmsTemplate;
  }

  @Scheduled(fixedRate = 1000L)
  public void send() {
    User user = new User();
    user.setEmail("lubos.krnac@gmail.com");
    user.setName("Lubos Krnac");

    log.info("Sending User: {} in role {}", user, ADMIN);
    //@formatter:off
    Message<User> userMessage = MessageBuilder
     .withPayload(user)
     .setHeader("role", ADMIN)
     .build();
    //@formatter:on

    jmsTemplate.convertAndSend("messageQueue", userMessage);
  }
}
