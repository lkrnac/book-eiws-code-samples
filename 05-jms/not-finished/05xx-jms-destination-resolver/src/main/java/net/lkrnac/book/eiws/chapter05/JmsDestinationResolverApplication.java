package net.lkrnac.book.eiws.chapter05;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import lombok.extern.slf4j.Slf4j;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class JmsDestinationResolverApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsDestinationResolverApplication.class, args);
  }

  @Bean
  public DestinationResolver destinationResolver() {
    return new DestinationResolver() {

      @Override
      public Destination resolveDestinationName(Session session,
          String destinationName, boolean pubSubDomain) throws JMSException {
        // TODO Auto-generated method stub
        return null;
      }
    };
  }

  @Bean
  public Destination destination1() {
    return new ActiveMQQueue("messageQueue1");
  }

  @Bean
  public Destination destination2() {
    return new ActiveMQQueue("messageQueue2");
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
