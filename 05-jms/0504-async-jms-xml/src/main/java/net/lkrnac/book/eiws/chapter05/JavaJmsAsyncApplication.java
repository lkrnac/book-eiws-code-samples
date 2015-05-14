package net.lkrnac.book.eiws.chapter05;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired
  private ConnectionFactory connectionFactory;

  @Bean
  public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
    return new ScheduledAnnotationBeanPostProcessor();
  }

  public static void main(String[] args) {
    SpringApplication.run(JavaJmsAsyncApplication.class, args);
  }
}
