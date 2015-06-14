package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;

@Configuration
public class JmsConfiguration {
  @Bean
  public JmsTransactionManager transactionManager(
      ConnectionFactory connectionFactory) {
    return new JmsTransactionManager(connectionFactory);
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
      ConnectionFactory connectionFactory,
      JmsTransactionManager transactionManager) {
    DefaultJmsListenerContainerFactory factory =
        new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setTransactionManager(transactionManager);
    return factory;
  }
}
