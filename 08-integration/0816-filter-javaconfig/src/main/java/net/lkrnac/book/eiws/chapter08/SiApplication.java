package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceVoidAnnotated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SiApplication {
  private static final String SIMPLE_MESSAGE = "simple message";

  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    SiWrapperServiceVoidAnnotated wrapperService =
        ctx.getBean(SiWrapperServiceVoidAnnotated.class);
    wrapperService.processText(SIMPLE_MESSAGE);
    wrapperService.processText("corrupted message");
    wrapperService.processText(SIMPLE_MESSAGE);
  }
}
