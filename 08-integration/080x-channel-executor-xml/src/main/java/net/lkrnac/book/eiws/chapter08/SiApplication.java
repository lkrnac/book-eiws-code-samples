package net.lkrnac.book.eiws.chapter08;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@SpringBootApplication
@ImportResource("classpath:si-config.xml")
public class SiApplication {
  private static final String MESSAGE2 = "message2";
  private static final String MESSAGE1 = "message1";

  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    SimpleService simpleService = ctx.getBean(SimpleService.class);
    simpleService.processText(MESSAGE1);
    log.info(MESSAGE1 + " sent");
    simpleService.processText(MESSAGE2);
    log.info(MESSAGE2 + " sent");
  }

  @Bean
  public Executor executor() {
    return Executors.newFixedThreadPool(10);
  }
}
