package net.lkrnac.book.eiws.chapter05.text;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
@ImportResource({ "classpath:spring-jms-config.xml",
    "classpath:spring-jms-listener.xml" })
public class JmsNamespaceAsyncApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsNamespaceAsyncApplication.class, args);
  }
}
