package net.lkrnac.book.eiws.chapter05.user;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserMessageSender {
  private JmsTemplate jmsTemplate;

  @Autowired
  public UserMessageSender(JmsTemplate jmsTemplate) {
    super();
    this.jmsTemplate = jmsTemplate;
  }

  @Scheduled(fixedRate = 1000)
  public void send() {
    User user = new User();
    user.setEmail("lubos.krnac@gmail.com");
    user.setName("Lubos Krnac");

    log.info("Sending message: {}", user);
    jmsTemplate.convertAndSend("queue/ExpiryQueue", user);
  }
}
