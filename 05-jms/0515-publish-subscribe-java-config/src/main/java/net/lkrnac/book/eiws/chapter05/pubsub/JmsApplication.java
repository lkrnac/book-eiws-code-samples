package net.lkrnac.book.eiws.chapter05.pubsub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JmsApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsApplication.class, args);
  }
}
