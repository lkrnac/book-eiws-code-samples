package net.lkrnac.book.eiws.chapter02.hessian.handlerservlet.javaconfig.client;

import net.lkrnac.book.eiws.chapter02.hessian.handlerservlet.javaconfig.shared.BarService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class ClientConfiguration {
  @Bean
  public HessianProxyFactoryBean hessianProxy() {
    HessianProxyFactoryBean hessianInvoker = new HessianProxyFactoryBean();
    hessianInvoker.setServiceUrl("http://localhost:10208/BarService");
    hessianInvoker.setServiceInterface(BarService.class);
    return hessianInvoker;
  }
}
