package net.lkrnac.book.eiws.chapter02.rmi.spring;

import net.lkrnac.book.eiws.chapter02.rmi.spring.service.BarService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@ComponentScan
public class ServiceConfiguration {
  @Bean
  public RmiServiceExporter registerService(BarService barService) {
    RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
    rmiServiceExporter.setServiceName("BarService");
    rmiServiceExporter.setService(barService);
    rmiServiceExporter.setServiceInterface(BarService.class);
    rmiServiceExporter.setRegistryPort(5000);

    return rmiServiceExporter;
  }
}
