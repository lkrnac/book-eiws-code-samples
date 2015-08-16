package net.lkrnac.book.eiws.chapter08.out;

import org.springframework.stereotype.Service;

@Service
public class WriteServiceWithError {
  public boolean writeAndIndicateSuccess(String message) {
    throw new IllegalStateException("error occured");
  }
}
