package net.lkrnac.book.eiws.chapter08;

import org.springframework.stereotype.Service;

@Service
public class WriteService {
  public boolean writeAndIndicateSuccess(String message) {
    throw new IllegalStateException("error occured");
  }
}
