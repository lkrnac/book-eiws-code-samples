package net.lkrnac.book.eiws.chapter03.ws.interceptor.config;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter03.ws.interceptor.client.WebServiceClient;
import net.lkrnac.book.eiws.chapter03.ws.interceptor.client.WsInterceptorClientConfiguration;
import net.lkrnac.book.eiws.chapter03.ws.interceptor.model.UserDetailsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import(WsInterceptorClientConfiguration.class)
public class WsInterceptorClientApplication {
  @Autowired
  private WebServiceClient wsClient;

  public static void main(String[] args) {
    SpringApplication.run(WsInterceptorClientApplication.class, args);
  }

  @PostConstruct
  public void test() {
    UserDetailsResponse userDetails =
        wsClient.getUserDetails("lubos.krnac@gmail.com");
    log.info("User Details: " + userDetails.getFirstName() + " "
        + userDetails.getLastName());
  }
}
