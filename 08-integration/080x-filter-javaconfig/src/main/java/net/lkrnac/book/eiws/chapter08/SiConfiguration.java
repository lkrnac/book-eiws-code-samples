package net.lkrnac.book.eiws.chapter08;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
@ImportResource("classpath:si-config.xml")
public class SiConfiguration {
  @Bean
  public MessageChannel inChannel() {
    return new DirectChannel();
  }

  @Bean
  public MessageChannel filteredChannel() {
    return new DirectChannel();
  }
}
