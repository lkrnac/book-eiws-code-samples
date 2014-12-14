package net.lkrnac.book.eiws.chapter02.httpinvoker.servlet.javaconfig.server;

import net.lkrnac.book.eiws.chapter02.httpinvoker.servlet.javaconfig.shared.BarService;

import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
  @Override
  public String serveBar(String param) {
    return "Bar service 0204 response to parameter: " + param;
  }
}
