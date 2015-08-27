package net.lkrnac.book.eiws.chapter08;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeFrom;
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
  public PollableChannel inChannel() {
    return new QueueChannel();
  }

  @Bean
  @BridgeFrom(value = "inChannel", poller = @Poller(fixedDelay = "100"))
  public MessageChannel bridgedChannel() {
    return new DirectChannel();
  }
}
