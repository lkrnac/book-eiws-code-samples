package net.lkrnac.book.eiws.chapter05;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

//@SpringBootApplication
@Configuration
@ImportResource("classpath:spring-jms.xml")
@ComponentScan
public class JavaJmsAsyncApplication {
  @Bean
  public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
    return new ScheduledAnnotationBeanPostProcessor();
  }

  @Bean
  public SimpleMessageHandler simpleMessageHandler() {
    return new SimpleMessageHandler() {

      @Override
      public void handleMessage(String message) {
        System.out.println(message);
      }
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(JavaJmsAsyncApplication.class, args);
  }
}
