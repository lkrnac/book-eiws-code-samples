package net.lkrnac.book.eiws.chapter02.hessian.dispatcherservlet.server;

import net.lkrnac.book.eiws.chapter02.hessian.dispatcherservlet.shared.BarService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ServerConfiguration {
  public static void main(String... args) {
    SpringApplication.run(ServerConfiguration.class);
  }

  @Bean(name = "/BarService")
  public HessianServiceExporter exportBarService(BarService barService) {
    HessianServiceExporter hessianServiceExporter =
        new HessianServiceExporter();
    hessianServiceExporter.setService(barService);
    hessianServiceExporter.setServiceInterface(BarService.class);
    return hessianServiceExporter;
  }
}
