package net.lkrnac.book.eiws.chapter05.pubsub;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JmsPublishSubscribeApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsPublishSubscribeApplication.class, args);
  }

  @Bean
  public ActiveMQTopic simpleTopic() {
    return new ActiveMQTopic("simpleTopic");
  }
}
