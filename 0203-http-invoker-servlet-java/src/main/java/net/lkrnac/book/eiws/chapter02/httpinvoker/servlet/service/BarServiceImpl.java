package net.lkrnac.book.eiws.chapter02.httpinvoker.servlet.service;

import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
  @Override
  public String serveBar(String param) {
    return "Bar service reponse to parameter: " + param;
  }
}
