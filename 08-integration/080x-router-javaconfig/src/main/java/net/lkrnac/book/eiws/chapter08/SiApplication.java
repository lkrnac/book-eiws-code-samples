package net.lkrnac.book.eiws.chapter08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SiApplication {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    SimpleService simpleService = ctx.getBean(SimpleService.class);
    simpleService.processText("message1");
    simpleService.processText("message2");
  }
}
