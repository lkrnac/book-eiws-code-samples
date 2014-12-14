package net.lkrnac.book.eiws.chapter02.httpinvoker.simple.server;

import net.lkrnac.book.eiws.chapter02.httpinvoker.simple.shared.BarService;

import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
  @Override
  public String serveBar(String param) {
    return "Bar service 0207 response to parameter: " + param;
  }
}
