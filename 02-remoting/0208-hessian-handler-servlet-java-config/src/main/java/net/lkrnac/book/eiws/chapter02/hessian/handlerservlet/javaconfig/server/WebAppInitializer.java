package net.lkrnac.book.eiws.chapter02.hessian.handlerservlet.javaconfig.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

public class WebAppInitializer implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootContext =
        new AnnotationConfigWebApplicationContext();
    rootContext.register(ServerConfiguration.class);
    rootContext.refresh();

    servletContext.addListener(new ContextLoaderListener(rootContext));
    ServletRegistration.Dynamic appServlet =
        servletContext.addServlet("barExporter",
            new HttpRequestHandlerServlet());
    appServlet.addMapping("/");
  }
}