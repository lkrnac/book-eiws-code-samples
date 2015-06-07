package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

//@Configuration
public class JmsListenerConfiguration {
  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
      ConnectionFactory connectionFactory) {
    DefaultJmsListenerContainerFactory factory =
        new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    // factory.setDestinationResolver(destinationResolver);
    // factory.setConcurrency("3-10");
    return factory;
  }
}
