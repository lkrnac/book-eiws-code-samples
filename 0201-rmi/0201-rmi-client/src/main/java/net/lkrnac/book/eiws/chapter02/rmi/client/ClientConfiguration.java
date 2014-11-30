package net.lkrnac.book.eiws.chapter02.rmi.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class ClientConfiguration {
  @Bean
  public BarService createBarServiceLink() {
    RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
    rmiProxyFactoryBean.setServiceUrl("rmi://localhost:5000/BarService");
    rmiProxyFactoryBean.setServiceInterface(BarService.class);
    rmiProxyFactoryBean.afterPropertiesSet();
    return (BarService) rmiProxyFactoryBean.getObject();
  }
}
