package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceMessage;

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

    SiWrapperServiceMessage wrapperService =
        ctx.getBean(SiWrapperServiceMessage.class);

    //@formatter:off
    Message<String> message1 = MessageBuilder.withPayload("message1")
            .setHeader("simpleHeader", "simple header")
            .build();
    wrapperService.processMessage(message1);

    Message<String> message2 = MessageBuilder.fromMessage(message1)
        .setPriority(10)
        .setCorrelationId(System.currentTimeMillis())
        .build();
    wrapperService.processMessage(message2);

    Message<String> message3 = MessageBuilder.withPayload("message3")
        .copyHeaders(message2.getHeaders())
        .removeHeader("simpleHeader")
        .build();
    wrapperService.processMessage(message3);

    Message<String> message4 = MessageBuilder.withPayload("message4")
        .setPriority(5)
        .copyHeadersIfAbsent(message2.getHeaders())
        .build();
    wrapperService.processMessage(message4);
    //@formatter:on
  }
}
