package net.lkrnac.book.eiws.chapter02.rmi.spring.service;

import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
  @Override
  public String serveBar(String param) {
    return "Bar service (Spring RMI Java config) reponse to parameter: "
        + param;
  }
}
