package net.lkrnac.book.eiws.chapter08.in;

import java.util.concurrent.Future;

public interface SiWrapperServiceFuture {
  Future<Boolean> processText(String text);
}
