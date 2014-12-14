package net.lkrnac.book.eiws.chapter02.httpinvoker.java;

import net.lkrnac.book.eiws.chapter02.httpinvoker.java.service.BarService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
// @ComponentScan
// @EnableAutoConfiguration
public class Application {
  // public static void main(String[] args) {
  // SpringApplication.run(Application.class, args);
  // }

  @Bean(name = "/AccountService")
  public HttpInvokerServiceExporter createServiceExporter(BarService barService) {
    HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
    serviceExporter.setServiceInterface(BarService.class);
    serviceExporter.setService(barService);
    return serviceExporter;
  }
}
