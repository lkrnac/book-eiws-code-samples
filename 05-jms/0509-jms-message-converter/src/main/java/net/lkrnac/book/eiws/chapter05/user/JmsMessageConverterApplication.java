package net.lkrnac.book.eiws.chapter05.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JmsMessageConverterApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsMessageConverterApplication.class, args);
  }
}
