package net.lkrnac.book.eiws.chapter08;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessagingTemplate;

@Configuration
public class SiConfiguration {
  @Bean
  public MessagingTemplate messagingTemplate() {
    MessagingTemplate messagingTemplate = new MessagingTemplate();
    messagingTemplate.setReceiveTimeout(1000);
    return messagingTemplate;
  }
}
