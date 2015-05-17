package net.lkrnac.book.eiws.chapter05;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.converter.MessagingMessageConverter;

@Configuration
@EnableJms
public class JmsConfiguration {
  @Bean
  public JmsMessagingTemplate jmsMessagingTemplate(
      ConnectionFactory connectionFactory, MessageConverter messageConverter) {
    JmsMessagingTemplate jmsMessagingTemplate =
        new JmsMessagingTemplate(connectionFactory);
    jmsMessagingTemplate.setJmsMessageConverter(messageConverter);
    return jmsMessagingTemplate;
  }

  @Bean
  public MessageConverter messageConverter() {
    MappingJackson2MessageConverter payloadConverter =
        new MappingJackson2MessageConverter();
    payloadConverter.setTargetType(MessageType.TEXT);
    payloadConverter.setTypeIdPropertyName("__type");

    MessagingMessageConverter messageConverter =
        new MessagingMessageConverter();
    messageConverter.setPayloadConverter(payloadConverter);
    return messageConverter;
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
      ConnectionFactory connectionFactory, MessageConverter messageConverter) {
    DefaultJmsListenerContainerFactory factory =
        new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter);
    return factory;
  }
}
