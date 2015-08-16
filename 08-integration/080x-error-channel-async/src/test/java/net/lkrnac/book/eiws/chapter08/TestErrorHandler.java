package net.lkrnac.book.eiws.chapter08;

import lombok.Getter;
import net.lkrnac.book.eiws.chapter08.out.ErrorHandler;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Profile("integration-test")
@Primary
@Getter
@Component
public class TestErrorHandler extends ErrorHandler {
  private Throwable lastError;

  @ServiceActivator(inputChannel = "customErrorChannel")
  public void handleException(Throwable throwable) {
    lastError = throwable;
  }
}
