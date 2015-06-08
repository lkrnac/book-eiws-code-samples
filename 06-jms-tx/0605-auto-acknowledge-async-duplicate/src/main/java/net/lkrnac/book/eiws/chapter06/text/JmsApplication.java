package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

@SpringBootApplication
@EnableJms
public class JmsApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(JmsApplication.class, args);
  }

  @Bean
  public SimpleJmsListenerContainerFactory jmsListenerContainerFactory(
      ConnectionFactory connectionFactory) {
    SimpleJmsListenerContainerFactory factory =
        new SimpleJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    return factory;
  }
}
