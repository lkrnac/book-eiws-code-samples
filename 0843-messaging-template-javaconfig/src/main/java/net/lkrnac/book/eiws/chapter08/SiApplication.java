package net.lkrnac.book.eiws.chapter08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.core.MessagingTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SiApplication {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    MessagingTemplate messagingTemplate = ctx.getBean(MessagingTemplate.class);
    boolean result = messagingTemplate.convertSendAndReceive(
        "inChannel", "simple message", Boolean.class);
    log.info("Result: " + result);
  }
}
