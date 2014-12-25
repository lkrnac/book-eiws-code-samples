package net.lkrnac.book.eiws.chapter02.httpinvoker.dispatcherservlet.server;

import net.lkrnac.book.eiws.chapter02.httpinvoker.dispatcherservlet.shared.BarService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ServerConfiguration {
  public static void main(String... args) {
    // SpringApplication.run(ServerConfiguration.class);
  }

  @Bean(name = "/BarService")
  public HttpInvokerServiceExporter exportBarService(BarService barService) {
    HttpInvokerServiceExporter httpInvokerServiceExporter =
        new HttpInvokerServiceExporter();
    httpInvokerServiceExporter.setService(barService);
    httpInvokerServiceExporter.setServiceInterface(BarService.class);
    return httpInvokerServiceExporter;
  }
}
