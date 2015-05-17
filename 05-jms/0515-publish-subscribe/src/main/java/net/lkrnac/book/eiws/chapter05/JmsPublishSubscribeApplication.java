package net.lkrnac.book.eiws.chapter05;

import javax.jms.ConnectionFactory;

import lombok.extern.slf4j.Slf4j;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class JmsPublishSubscribeApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsPublishSubscribeApplication.class, args);
  }

  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
    JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
    // jmsTemplate.setPubSubDomain(true);
    jmsTemplate.setSessionTransacted(true);
    // jmsTemplate.set
    return jmsTemplate;
  }

  // @Bean
  // public ActiveMQConnectionFactory connectionFactory() {
  // ActiveMQConnectionFactory connectionFactory =
  // new ActiveMQConnectionFactory();
  // connectionFactory.setBrokerURL("vm://localhost");
  // connectionFactory.se
  // return connectionFactory;
  // }

  @Bean
  public ActiveMQTopic simpleTopic() {
    return new ActiveMQTopic("simpleTopic");
  }

  @Bean
  public PubSubHandler pubSubHandler() {
    return new PubSubHandler() {
      @Override
      public void handleMessage(int listenerId, String message) {
        log.info("Message Received: {} via listener {}", message, listenerId);
      }
    };
  }
}
