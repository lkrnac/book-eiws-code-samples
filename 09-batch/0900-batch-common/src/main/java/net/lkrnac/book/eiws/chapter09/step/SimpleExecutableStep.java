package net.lkrnac.book.eiws.chapter09.step;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleExecutableStep {
  public void executeStep(String stepName) {
    log.info(stepName + " executed");
  }
}
