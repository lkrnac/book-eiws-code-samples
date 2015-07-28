package net.lkrnac.book.eiws.chapter08;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class SiApplication {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    SimpleService simpleService = ctx.getBean(SimpleService.class);
    boolean resultSuccess =
        simpleService.processText("messageSuccess;messageFail;messageSuccess");
    log.info("Result: " + resultSuccess);

    boolean resultFail =
        simpleService.processText("messageSuccess;messageFail;");
    log.info("Result: " + resultFail);
  }
}
