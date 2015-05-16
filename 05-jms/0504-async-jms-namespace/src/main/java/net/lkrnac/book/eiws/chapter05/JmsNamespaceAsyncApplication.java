package net.lkrnac.book.eiws.chapter05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
@ImportResource("classpath:spring-jms.xml")
public class JmsNamespaceAsyncApplication {
  private static final Logger LOG = LoggerFactory
      .getLogger(JmsNamespaceAsyncApplication.class);

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsNamespaceAsyncApplication.class, args);
  }

  @Bean
  public SimpleMessageHandler simpleMessageHandler() {
    return new SimpleMessageHandler() {
      @Override
      public void handleMessage(String message) {
        LOG.info("Message Received: {}", message);
      }
    };
  }
}
