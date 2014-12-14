package net.lkrnac.book.eiws.chapter02.rmi.spring.javaconfig.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@ComponentScan
public class ServiceConfiguration {
  private static final int RMI_PORT = 10202;

  @Bean
  public RmiServiceExporter registerService(BarService barService) {
    RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
    rmiServiceExporter.setServiceName("BarService");
    rmiServiceExporter.setService(barService);
    rmiServiceExporter.setServiceInterface(BarService.class);
    rmiServiceExporter.setRegistryPort(RMI_PORT);

    return rmiServiceExporter;
  }
}
