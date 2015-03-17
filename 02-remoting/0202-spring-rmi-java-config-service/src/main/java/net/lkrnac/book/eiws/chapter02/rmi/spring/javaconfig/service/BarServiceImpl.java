package net.lkrnac.book.eiws.chapter02.rmi.spring.javaconfig.service;

import net.lkrnac.book.eiws.chapter02.rmi.spring.javaconfig.BarService;

import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
  @Override
  public String serveBar(String param) {
    return "Bar service 0202 response to parameter: " + param;
  }
}
