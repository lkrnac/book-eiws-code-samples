package net.lkrnac.book.eiws.chapter05.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JmsCustomConverterApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsCustomConverterApplication.class, args);
  }
}
