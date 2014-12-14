package net.lkrnac.book.eiws.chapter02.httpinvoker;

import org.springframework.stereotype.Service;

@Service("barService")
public class BarServiceImpl implements BarService {
  public String serveBar(String param) {
    return "Bar service reponse to parameter: " + param;
  }
}
