package net.lkrnac.book.eiws.chapter07;

import org.springframework.boot.SpringApplication;

public class JtaApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(JtaConfiguration.class, args);
  }
}
