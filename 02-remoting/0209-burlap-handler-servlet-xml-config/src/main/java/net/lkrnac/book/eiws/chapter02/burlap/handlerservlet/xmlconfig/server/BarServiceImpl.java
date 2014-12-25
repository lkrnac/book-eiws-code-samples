package net.lkrnac.book.eiws.chapter02.burlap.handlerservlet.xmlconfig.server;

import net.lkrnac.book.eiws.chapter02.burlap.handlerservlet.xmlconfig.shared.BarService;

import org.springframework.stereotype.Service;

@Service("barService")
public class BarServiceImpl implements BarService {
  public static void main(String... args) {
    // this is war package, just making spring boot repackage happy
  }

  public String serveBar(String param) {
    return "Bar service 0209 response to parameter: " + param;
  }
}
