package net.lkrnac.book.eiws.chapter05;

import lombok.extern.slf4j.Slf4j;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@ComponentScan
@EnableScheduling
public class JavaConfigJmsAsyncApplication {
  public static void main(String[] args) throws Exception {
    BrokerService broker = new BrokerService();
    broker.addConnector("tcp://localhost:10506");
    broker.start();

    SpringApplication.run(JavaConfigJmsAsyncApplication.class, args);
  }

  @Bean
  public SimpleMessageHandler simpleMessageHandler() {
    return new SimpleMessageHandler() {
      @Override
      public void handleMessage(String message) {
        log.info("Message Received: {}", message);
      }
    };
  }
}
