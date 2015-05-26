package net.lkrnac.book.eiws.chapter05;

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
public class JmsXmlAsyncApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsXmlAsyncApplication.class, args);
  }
}
