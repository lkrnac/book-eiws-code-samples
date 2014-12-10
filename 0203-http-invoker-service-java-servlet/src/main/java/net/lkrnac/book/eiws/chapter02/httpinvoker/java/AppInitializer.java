package net.lkrnac.book.eiws.chapter02.httpinvoker.java;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends
    AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { ServletConfiguration.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}