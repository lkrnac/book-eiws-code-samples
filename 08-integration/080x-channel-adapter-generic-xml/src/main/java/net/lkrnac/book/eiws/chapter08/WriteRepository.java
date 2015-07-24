package net.lkrnac.book.eiws.chapter08;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class WriteRepository {
  public void write(String message) {
    log.info(message);
  }
}
