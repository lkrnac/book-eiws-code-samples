package net.lkrnac.book.eiws.chapter03.ws.javaconfig.client;

import org.springframework.boot.SpringApplication;

public class Application {

  public static void main(String[] args) {
    SpringApplication.run("classpath:ws-client-config.xml", args);
  }
}
