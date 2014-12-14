package net.lkrnac.book.eiws.chapter02.httpinvoker.bean.client;

import net.lkrnac.book.eiws.chapter02.httpinvoker.bean.shared.BarService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class ClientConfiguration {
  @Bean
  public HttpInvokerProxyFactoryBean httpInvokerProxy() {
    HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
    httpInvoker.setServiceUrl("http://localhost:10206/BarService");
    httpInvoker.setServiceInterface(BarService.class);
    return httpInvoker;
  }
}
