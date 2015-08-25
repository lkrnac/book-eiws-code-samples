package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceVoid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:si-config.xml")
public class SiApplication {
  private static final String MESSAGE2 = "message2";
  private static final String MESSAGE1 = "message1";

  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    SiWrapperServiceVoid wrapperService =
        ctx.getBean(SiWrapperServiceVoid.class);
    wrapperService.processText(MESSAGE1);
    wrapperService.processText(MESSAGE2);
  }
}
