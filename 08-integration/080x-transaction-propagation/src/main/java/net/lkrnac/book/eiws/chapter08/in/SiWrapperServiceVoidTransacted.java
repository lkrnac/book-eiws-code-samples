package net.lkrnac.book.eiws.chapter08.in;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SiWrapperServiceVoidTransacted {
  void processText(String text);
}
