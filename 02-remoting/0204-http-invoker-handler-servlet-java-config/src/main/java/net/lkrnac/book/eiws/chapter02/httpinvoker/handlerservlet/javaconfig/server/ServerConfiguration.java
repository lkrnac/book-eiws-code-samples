package net.lkrnac.book.eiws.chapter02.httpinvoker.handlerservlet.javaconfig.server;

import net.lkrnac.book.eiws.chapter02.httpinvoker.handlerservlet.javaconfig.shared.BarService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@ComponentScan
public class ServerConfiguration {
  public static void main(String... args) {
    // this is war package, just making spring boot repackage happy
  }

  @Bean
  public HttpInvokerServiceExporter barExporter(BarService barService) {
    HttpInvokerServiceExporter httpInvokerServiceExporter =
        new HttpInvokerServiceExporter();
    httpInvokerServiceExporter.setService(barService);
    httpInvokerServiceExporter.setServiceInterface(BarService.class);
    return httpInvokerServiceExporter;
  }
}
