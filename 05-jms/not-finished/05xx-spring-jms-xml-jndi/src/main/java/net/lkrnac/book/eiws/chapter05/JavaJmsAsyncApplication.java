package net.lkrnac.book.eiws.chapter05;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

@ImportResource("classpath:spring-jndi.xml")
// @SpringBootApplication
public class JavaJmsAsyncApplication {

  // @Autowired
  // private ConnectionFactory connectionFactory;

  @Bean
  public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
    return new ScheduledAnnotationBeanPostProcessor();
  }

  // @Bean
  // public JmsTemplate jmsTemplate() {
  // return new JmsTemplate();
  // };

  @Bean
  public DefaultMessageListenerContainer messageListener()
      throws NamingException {
    DefaultMessageListenerContainer container =
        new DefaultMessageListenerContainer();
    ConnectionFactory connectionFactory =
        (ConnectionFactory) new InitialContext()
            .lookup("jms:jms/ConnectionFactory");

    container.setConnectionFactory(connectionFactory);
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
