package net.lkrnac.book.eiws.chapter02.httpinvoker.simple.client;

import net.lkrnac.book.eiws.chapter02.httpinvoker.simple.shared.BarService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
@EnableAutoConfiguration
public class ClientConfiguration {
  public static void main(String... args) {
    SpringApplication.run(ClientConfiguration.class);
  }

  @Bean
  public HttpInvokerProxyFactoryBean httpInvokerProxy() {
    HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
    httpInvoker.setServiceUrl("http://localhost:10207/BarService");
    httpInvoker.setServiceInterface(BarService.class);
    return httpInvoker;
  }
}
