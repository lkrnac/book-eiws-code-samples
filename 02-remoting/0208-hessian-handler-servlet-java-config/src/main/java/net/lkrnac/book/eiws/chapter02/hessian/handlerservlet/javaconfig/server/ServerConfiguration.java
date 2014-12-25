package net.lkrnac.book.eiws.chapter02.hessian.handlerservlet.javaconfig.server;

import net.lkrnac.book.eiws.chapter02.hessian.handlerservlet.javaconfig.shared.BarService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
@ComponentScan
public class ServerConfiguration {
  public static void main(String... args) {
    // this is war package, just making spring boot repackage happy
  }

  @Bean
  public HessianServiceExporter barExporter(BarService barService) {
    HessianServiceExporter hessianServiceExporter =
        new HessianServiceExporter();
    hessianServiceExporter.setService(barService);
    hessianServiceExporter.setServiceInterface(BarService.class);
    return hessianServiceExporter;
  }
}
