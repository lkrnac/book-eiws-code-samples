package net.lkrnac.book.eiws.chapter07;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
public class JtaApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(JtaApplication.class, args);
  }
}
