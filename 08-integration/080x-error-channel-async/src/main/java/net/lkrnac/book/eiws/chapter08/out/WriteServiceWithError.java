package net.lkrnac.book.eiws.chapter08.out;

import org.springframework.stereotype.Service;

@Service
public class WriteServiceWithError {
  public void write(String message) {
    throw new IllegalStateException("error occurred");
  }
}
