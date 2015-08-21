package net.lkrnac.book.eiws.chapter08.out;

import org.springframework.stereotype.Repository;

@Repository
public class WriteRepositoryWithHeaders {
  public int writeWithHeader(String message, String simpleHeader) {
    return 1;
  }
}
