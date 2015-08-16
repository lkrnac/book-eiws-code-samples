package net.lkrnac.book.eiws.chapter08;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceWithHeaders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@Slf4j
@SpringBootApplication
@ImportResource("classpath:si-config.xml")
public class SiApplication {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    Message<String> message =
        MessageBuilder.withPayload("simple message")
            .setHeader("simpleHeader", "simple header").build();

    SiWrapperServiceWithHeaders wrapperService =
        ctx.getBean(SiWrapperServiceWithHeaders.class);
    wrapperService.processMessage(message);
    log.info(message + " sent");
  }
}
