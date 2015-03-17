package net.lkrnac.book.eiws.chapter02.rmi.spring.javaconfig.client;

import net.lkrnac.book.eiws.chapter02.rmi.spring.javaconfig.BarService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class ClientConfiguration {
  @Bean
  public BarService createBarServiceLink() {
    RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
    rmiProxyFactoryBean.setServiceUrl("rmi://localhost:10202/BarService");
    rmiProxyFactoryBean.setServiceInterface(BarService.class);
    rmiProxyFactoryBean.afterPropertiesSet();
    return (BarService) rmiProxyFactoryBean.getObject();
  }
}
