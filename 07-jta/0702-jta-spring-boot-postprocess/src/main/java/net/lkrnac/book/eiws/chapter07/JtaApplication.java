package net.lkrnac.book.eiws.chapter07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JtaApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(JtaApplication.class, args);
  }
}
