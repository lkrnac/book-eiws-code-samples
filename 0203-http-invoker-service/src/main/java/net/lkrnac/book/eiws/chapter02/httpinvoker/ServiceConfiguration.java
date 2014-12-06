package net.lkrnac.book.eiws.chapter02.httpinvoker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
public class ServiceConfiguration {
  @Bean
  public HttpInvokerServiceExporter createServiceExporter() {
    HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
    // serviceExporter.
    return serviceExporter;
  }
}
