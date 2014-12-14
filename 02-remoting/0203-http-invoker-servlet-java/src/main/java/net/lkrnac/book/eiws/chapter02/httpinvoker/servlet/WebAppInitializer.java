package net.lkrnac.book.eiws.chapter02.httpinvoker.servlet;

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
    // Create the root appcontext
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(SpringContext.class);
    // since we registered RootConfig instead of passing it to the constructor
    rootContext.refresh();

    // Manage the lifecycle of the root appcontext
    servletContext.addListener(new ContextLoaderListener(rootContext));

    // The main Spring MVC servlet.
    ServletRegistration.Dynamic appServlet = servletContext.addServlet(
        "barExporter", new HttpRequestHandlerServlet());
    appServlet.addMapping("/");
  }
}