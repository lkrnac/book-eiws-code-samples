package net.lkrnac.book.eiws.chapter02.hessian.dispatcherservlet.server;

import net.lkrnac.book.eiws.chapter02.hessian.dispatcherservlet.shared.BarService;

import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
  @Override
  public String serveBar(String param) {
    return "Bar service 0210 response to parameter: " + param;
  }
}
