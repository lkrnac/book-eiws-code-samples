package net.lkrnac.book.eiws.chapter01.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
public class ScheduledConfiguration {
  public static void main(String[] args) {
    SpringApplication.run(ScheduledConfiguration.class, args);
  }
}
