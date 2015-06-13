package net.lkrnac.book.eiws.chapter06.text;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring-jms-config.xml")
public class JmsApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(JmsApplication.class, args);
  }
}
