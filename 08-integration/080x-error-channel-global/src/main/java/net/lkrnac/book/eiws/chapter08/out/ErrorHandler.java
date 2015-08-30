package net.lkrnac.book.eiws.chapter08.out;

import lombok.extern.slf4j.Slf4j;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorHandler {
  @ServiceActivator(inputChannel = "errorChannel")
  public void handleException(Throwable throwable) {
    log.error("Error occurred: ", throwable);
  }
}
