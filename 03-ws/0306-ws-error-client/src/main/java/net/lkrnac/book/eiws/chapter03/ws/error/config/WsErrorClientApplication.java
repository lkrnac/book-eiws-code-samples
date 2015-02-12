package net.lkrnac.book.eiws.chapter03.ws.error.config;

import javax.annotation.PostConstruct;

import net.lkrnac.book.eiws.chapter03.ws.error.client.WebServiceClient;
import net.lkrnac.book.eiws.chapter03.ws.error.client.WsErrorClientConfiguration;
import net.lkrnac.book.eiws.chapter03.ws.error.model.UserDetailsResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WsErrorClientConfiguration.class)
public class WsErrorClientApplication {
  private static final Logger log = LoggerFactory
      .getLogger(WebServiceClient.class);

  public static void main(String[] args) {
    SpringApplication.run(WsErrorClientApplication.class, args);
  }

  @Autowired
  private WebServiceClient wsClient;

  @PostConstruct
  public void test() {
    UserDetailsResponse userDetails =
        wsClient.getUserDetails("lubos.krnac@gmail.com");
    log.info("User Details: " + userDetails.getFirstName() + " "
        + userDetails.getLastName());
  }
}
