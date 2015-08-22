package net.lkrnac.book.eiws.chapter08;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class SiApplication {
  public static void main(String[] args) throws InterruptedException {
    new GenericXmlApplicationContext(new ClassPathResource("si-config.xml"));
  }
}
