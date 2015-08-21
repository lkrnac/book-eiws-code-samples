package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceWithHeaders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@SpringBootApplication
@ImportResource("classpath:si-config.xml")
public class SiApplication {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    SiWrapperServiceWithHeaders wrapperService =
        ctx.getBean(SiWrapperServiceWithHeaders.class);

    Message<String> message =
        MessageBuilder.withPayload("simple message")
            .setHeader("simpleHeader", "simple header").setPriority(10).build();

    wrapperService.processMessage(message);
  }
}
