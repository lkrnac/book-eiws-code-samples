package net.lkrnac.book.eiws.chapter08;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeTo;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@Configuration
@IntegrationComponentScan
public class SiConfiguration {
  @Bean
  @BridgeTo(value = "bridgedChannel", poller = @Poller(fixedDelay = "100"))
  public PollableChannel inChannel() {
    return new QueueChannel();
  }

  @Bean
  public MessageChannel bridgedChannel() {
    return new DirectChannel();
  }
}
