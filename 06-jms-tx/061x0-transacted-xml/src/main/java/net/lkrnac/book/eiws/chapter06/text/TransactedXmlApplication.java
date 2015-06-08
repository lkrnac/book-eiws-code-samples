package net.lkrnac.book.eiws.chapter06.text;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ImportResource("classpath:spring-jms-config.xml")
@SpringBootApplication
public class TransactedXmlApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(TransactedXmlApplication.class, args);
  }
}
