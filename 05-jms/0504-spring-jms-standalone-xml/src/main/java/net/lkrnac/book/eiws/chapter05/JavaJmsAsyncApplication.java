package net.lkrnac.book.eiws.chapter05;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

@SpringBootApplication
public class JavaJmsAsyncApplication {

  @Autowired
  private ConnectionFactory connectionFactory;

  @Bean
  public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
    return new ScheduledAnnotationBeanPostProcessor();
  }

  @Bean
  public DefaultMessageListenerContainer messageListener() {
    DefaultMessageListenerContainer container =
        new DefaultMessageListenerContainer();
    container.setConnectionFactory(this.connectionFactory);
    container.setDestinationName("messageQueue");
    container.setMessageListener(new MessageListener() {
      @Override
      public void onMessage(Message message) {
        try {
          TextMessage textMessage = (TextMessage) message;
          System.out.println(textMessage.getText());
        } catch (JMSException ex) {
          ex.printStackTrace();
        }
      }
    });
    return container;
  }

  public static void main(String[] args) {
    SpringApplication.run(JavaJmsAsyncApplication.class, args);
  }
}
