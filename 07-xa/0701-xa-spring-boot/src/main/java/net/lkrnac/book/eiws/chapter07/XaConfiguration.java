package net.lkrnac.book.eiws.chapter07;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

@Configuration
public class XaConfiguration {
  @Bean
  public SimpleJmsListenerContainerFactory jmsListenerContainerFactory(
      ConnectionFactory connectionFactory) {
    SimpleJmsListenerContainerFactory factory =
        new SimpleJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    return factory;
  }
}
