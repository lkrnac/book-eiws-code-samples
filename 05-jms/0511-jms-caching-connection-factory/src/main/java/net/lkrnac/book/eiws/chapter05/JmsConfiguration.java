package net.lkrnac.book.eiws.chapter05;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
@EnableJms
public class JmsConfiguration {
  @Bean
  public ConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory activeMqConnectionFactory =
        new ActiveMQConnectionFactory();
    activeMqConnectionFactory.setBrokerURL("vm://localhost");

    CachingConnectionFactory connectionFactory =
        new CachingConnectionFactory(activeMqConnectionFactory);
    return connectionFactory;
  }
}
