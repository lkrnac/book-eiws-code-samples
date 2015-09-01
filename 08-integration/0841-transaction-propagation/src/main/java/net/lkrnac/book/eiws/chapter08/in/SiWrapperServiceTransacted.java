package net.lkrnac.book.eiws.chapter08.in;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SiWrapperServiceTransacted {
  boolean processText(String text);
}
