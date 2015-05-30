package net.lkrnac.book.eiws.chapter05;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service("simpleService")
public class SimpleService {
  public void processText(String message) {
    log.info("Message received: {}", message);
  }
}
