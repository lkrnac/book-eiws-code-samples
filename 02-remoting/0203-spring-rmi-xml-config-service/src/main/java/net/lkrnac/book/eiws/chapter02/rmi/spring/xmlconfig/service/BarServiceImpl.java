package net.lkrnac.book.eiws.chapter02.rmi.spring.xmlconfig.service;

import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
  @Override
  public String serveBar(String param) {
    return "Bar service 0203 response to parameter: " + param;
  }
}
