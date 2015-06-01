package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfiguration {
  @Bean
  public JmsTransactionManager transactionManager(
      ConnectionFactory connectionFactory) {
    return new JmsTransactionManager(connectionFactory);
  }

  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
    JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
    jmsTemplate.setSessionTransacted(true);
    jmsTemplate.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
    return jmsTemplate;
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
      ConnectionFactory connectionFactory) {
    DefaultJmsListenerContainerFactory factory =
        new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setSessionTransacted(true);
    factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
    return factory;
  }
}
