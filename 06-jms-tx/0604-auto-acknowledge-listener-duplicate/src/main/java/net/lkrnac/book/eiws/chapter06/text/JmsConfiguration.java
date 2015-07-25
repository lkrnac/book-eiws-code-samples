package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.util.ErrorHandler;

@Configuration
public class JmsConfiguration {
  @Bean
  public SimpleJmsListenerContainerFactory jmsListenerContainerFactory(
      ConnectionFactory connectionFactory) {
    SimpleJmsListenerContainerFactory factory =
        new SimpleJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setErrorHandler(new ErrorHandler() {
      @Override
      public void handleError(Throwable throwable) {
        throw new RuntimeException(throwable);
      }
    });
    return factory;
  }
}
