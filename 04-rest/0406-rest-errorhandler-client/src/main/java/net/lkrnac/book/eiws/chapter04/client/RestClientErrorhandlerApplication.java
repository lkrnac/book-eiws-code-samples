package net.lkrnac.book.eiws.chapter04.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ClientConfiguration.class)
public class RestClientErrorhandlerApplication {
  public static void main(String... args) {
    SpringApplication.run(RestClientErrorhandlerApplication.class, args);
  }
}
