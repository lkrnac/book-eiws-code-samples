package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

@Configuration
public class JmsConfiguration {
  @Bean
  public SimpleJmsListenerContainerFactory jmsListenerContainerFactory(
      ConnectionFactory connectionFactory) {
    SimpleJmsListenerContainerFactory factory =
        new SimpleJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    return factory;
  }
}
