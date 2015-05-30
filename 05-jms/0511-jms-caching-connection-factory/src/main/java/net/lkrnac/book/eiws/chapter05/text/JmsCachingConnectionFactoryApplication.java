package net.lkrnac.book.eiws.chapter05.text;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JmsCachingConnectionFactoryApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(JmsCachingConnectionFactoryApplication.class, args);
  }
}
