package net.lkrnac.book.eiws.chapter03.ws.objectfactory.config;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter03.ws.objectfactory.client.WebServiceClient;
import net.lkrnac.book.eiws.chapter03.ws.objectfactory.client.WsObjectFactoryClientConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import(WsObjectFactoryClientConfiguration.class)
public class WsObjectFactoryClientApplication {
  @Autowired
  private WebServiceClient wsClient;

  public static void main(String[] args) {
    SpringApplication.run(WsObjectFactoryClientApplication.class, args);
  }

  @PostConstruct
  public void test() {
    // UserDetailsResponse userDetails =
    // wsClient.getUserDetails("lubos.krnac@gmail.com");
    // log.info("User Details: " + userDetails.getFirstName() + " "
    // + userDetails.getLastName());
  }
}
