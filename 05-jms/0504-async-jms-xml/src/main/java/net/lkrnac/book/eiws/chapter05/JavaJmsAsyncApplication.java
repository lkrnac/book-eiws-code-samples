package net.lkrnac.book.eiws.chapter05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@ComponentScan
@ImportResource("classpath:spring-jms.xml")
@EnableScheduling
public class JavaJmsAsyncApplication {
  private static final String SIMPLE_MESSAGE = "simple message";
  private static final Logger LOG = LoggerFactory
      .getLogger(JavaJmsAsyncApplication.class);

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JavaJmsAsyncApplication.class, args);
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

  @Autowired
  private SimpleMessageSender messageSender;

  @Scheduled(initialDelay = 1000, fixedRate = 1000L)
  public void sendMessage() throws InterruptedException {
    LOG.info("Sending message: {}", SIMPLE_MESSAGE);
    messageSender.send(SIMPLE_MESSAGE);
  }
}
