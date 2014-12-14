package net.lkrnac.book.eiws.chapter02.rmi.spring.xmlconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;

public class Application {
  public static void main(String... args) {
    SpringApplication.run(new ClassPathResource("bar-service-context.xml"),
        args);
  }
}
