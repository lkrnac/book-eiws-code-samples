package net.lkrnac.book.eiws.chapter02.httpinvoker.servlet.xmlconfig.server;

import net.lkrnac.book.eiws.chapter02.httpinvoker.servlet.xmlconfig.shared.BarService;

import org.springframework.stereotype.Service;

@Service("barService")
public class BarServiceImpl implements BarService {
  public String serveBar(String param) {
    return "Bar service 0205 response to parameter: " + param;
  }
}
