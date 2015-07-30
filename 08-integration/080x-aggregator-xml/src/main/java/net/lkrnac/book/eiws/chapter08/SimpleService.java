package net.lkrnac.book.eiws.chapter08;

import java.util.concurrent.Future;

public interface SimpleService {
  Future<Boolean> processText(String text);
}
