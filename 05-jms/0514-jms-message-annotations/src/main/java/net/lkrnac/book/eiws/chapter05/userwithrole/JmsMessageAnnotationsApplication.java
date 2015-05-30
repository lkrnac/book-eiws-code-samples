package net.lkrnac.book.eiws.chapter05.userwithrole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JmsMessageAnnotationsApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsMessageAnnotationsApplication.class, args);
  }
}
