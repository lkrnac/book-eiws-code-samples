package net.lkrnac.book.eiws.chapter05;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
@EnableJms
public class JmsConfiguration {
  @Bean
  public ConnectionFactory connectionFactory() {
    return new ActiveMQConnectionFactory("tcp://localhost:10506");
  }

  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
    return new JmsTemplate(connectionFactory);
  }

  @Bean
  public DefaultMessageListenerContainer defaultMessageListenerContainer(
      ConnectionFactory connectionFactory,
      SimpleMessageListener simpleMessageListener) {
    DefaultMessageListenerContainer listenerContainer =
        new DefaultMessageListenerContainer();
    listenerContainer.setConnectionFactory(connectionFactory);
    listenerContainer.setDestinationName("messageQueue");
    listenerContainer.setMessageListener(simpleMessageListener);
    return listenerContainer;
  }
}
