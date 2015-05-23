package net.lkrnac.book.eiws.chapter05;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
public class JmsListenerConfiguration {
  @Bean
  public DefaultMessageListenerContainer defaultMessageListenerContainer(
      ConnectionFactory connectionFactory,
      SimpleMessageListener simpleMessageListener, Queue queue) {
    DefaultMessageListenerContainer listenerContainer =
        new DefaultMessageListenerContainer();
    listenerContainer.setConnectionFactory(connectionFactory);
    listenerContainer.setDestinationName("messageQueue");
    listenerContainer.setDestination(queue);
    listenerContainer.setMessageListener(simpleMessageListener);
    return listenerContainer;
  }
}
