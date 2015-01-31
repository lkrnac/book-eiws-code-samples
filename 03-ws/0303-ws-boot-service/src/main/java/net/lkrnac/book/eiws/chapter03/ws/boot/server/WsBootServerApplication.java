package net.lkrnac.book.eiws.chapter03.ws.boot.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class WsBootServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(WsBootServerApplication.class, args);
  }
}