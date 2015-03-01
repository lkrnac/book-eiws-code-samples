package net.lkrnac.book.eiws.chapter04.xmlconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class RestJaxrsApplication extends SpringBootServletInitializer {
  public static void main(String... args) {
    SpringApplication.run(RestJaxrsApplication.class, args);
  }
}
