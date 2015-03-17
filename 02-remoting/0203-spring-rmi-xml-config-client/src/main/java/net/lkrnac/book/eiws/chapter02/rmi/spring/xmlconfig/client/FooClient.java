package net.lkrnac.book.eiws.chapter02.rmi.spring.xmlconfig.client;

import javax.annotation.PostConstruct;

import net.lkrnac.book.eiws.chapter02.rmi.spring.xmlconfig.BarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FooClient {
  private final BarService barService;

  @Autowired
  public FooClient(BarService barService) {
    this.barService = barService;
  }

  @PostConstruct
  public void callBarService() {
    System.out.println(barService.serveBar("FooClient"));
  }
}
