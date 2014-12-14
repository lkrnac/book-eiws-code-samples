package net.lkrnac.book.eiws.chapter02.httpinvoker.bean.server;

import net.lkrnac.book.eiws.chapter02.httpinvoker.bean.shared.BarService;

import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
  @Override
  public String serveBar(String param) {
    return "Bar service 0206 response to parameter: " + param;
  }
}
