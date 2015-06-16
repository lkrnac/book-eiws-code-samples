package net.lkrnac.book.eiws.chapter05.text;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@EnableScheduling
public class JavaConfigJmsAsyncApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(JavaConfigJmsAsyncApplication.class, args);
  }
}
