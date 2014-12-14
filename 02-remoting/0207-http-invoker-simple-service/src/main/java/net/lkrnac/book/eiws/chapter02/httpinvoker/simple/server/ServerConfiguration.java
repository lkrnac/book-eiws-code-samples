package net.lkrnac.book.eiws.chapter02.httpinvoker.simple.server;

import java.util.HashMap;
import java.util.Map;

import net.lkrnac.book.eiws.chapter02.httpinvoker.simple.shared.BarService;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerServiceExporter;
import org.springframework.remoting.support.SimpleHttpServerFactoryBean;

import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
@Configuration
@ComponentScan
public class ServerConfiguration {
  public static void main(String... args) {
    SpringApplication.run(ServerConfiguration.class);
  }

  @Bean
  public SimpleHttpInvokerServiceExporter barExporter(BarService barService) {
    SimpleHttpInvokerServiceExporter barExporter =
        new SimpleHttpInvokerServiceExporter();
    barExporter.setService(barService);
    barExporter.setServiceInterface(BarService.class);
    return barExporter;
  }

  @Bean
  public SimpleHttpServerFactoryBean httpServer(
      SimpleHttpInvokerServiceExporter barExporter) {
    SimpleHttpServerFactoryBean httpServer = new SimpleHttpServerFactoryBean();
    Map<String, HttpHandler> endpoints = new HashMap<>();
    endpoints.put("/BarService", barExporter);
    httpServer.setContexts(endpoints);
    httpServer.setPort(10207);
    return httpServer;
  }
}
