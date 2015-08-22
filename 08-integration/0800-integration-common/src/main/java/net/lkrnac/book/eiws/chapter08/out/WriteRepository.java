package net.lkrnac.book.eiws.chapter08.out;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class WriteRepository {
  public int persist(String text) {
    log.info("Text persisted: " + text);
    return 1;
  }
}
