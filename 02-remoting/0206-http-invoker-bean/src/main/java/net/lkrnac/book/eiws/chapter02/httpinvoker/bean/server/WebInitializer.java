package net.lkrnac.book.eiws.chapter02.httpinvoker.bean.server;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {
  @Override
  protected SpringApplicationBuilder configure(
      SpringApplicationBuilder application) {
    return application.sources(ServerConfiguration.class);
  }
}