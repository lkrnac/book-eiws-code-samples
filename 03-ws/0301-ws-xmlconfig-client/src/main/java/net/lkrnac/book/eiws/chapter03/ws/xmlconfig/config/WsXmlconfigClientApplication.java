package net.lkrnac.book.eiws.chapter03.ws.xmlconfig.config;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter03.ws.xmlconfig.client.WebServiceClient;
import net.lkrnac.book.eiws.chapter03.ws.xmlconfig.model.UserDetailsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@Configuration
@ImportResource("classpath:ws-client-config.xml")
@EnableAutoConfiguration
public class WsXmlconfigClientApplication {
  @Autowired
  private WebServiceClient wsClient;

  public static void main(String[] args) {
    SpringApplication.run(WsXmlconfigClientApplication.class, args);
  }

  @PostConstruct
  public void test() {
    UserDetailsResponse userDetails =
        wsClient.getUserDetails("lubos.krnac@gmail.com");
    log.info("User Details: " + userDetails.getFirstName() + " "
        + userDetails.getLastName());
  }
}
