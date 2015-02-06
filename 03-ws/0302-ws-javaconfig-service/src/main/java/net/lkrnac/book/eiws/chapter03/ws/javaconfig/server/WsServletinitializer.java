package net.lkrnac.book.eiws.chapter03.ws.javaconfig.server;

import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

public class WsServletinitializer extends
    AbstractAnnotationConfigMessageDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { ServerConfiguration.class };
  }

}
