package net.lkrnac.book.eiws.chapter09.step;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleExecutablePoint {
  public void execute(String message) {
    log.info(message);
  }
}
