package net.lkrnac.book.eiws.chapter03.ws.boot.client;

import javax.annotation.PostConstruct;

import net.lkrnac.book.eiws.chapter03.ws.boot.model.UserDetailsResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test {
  private Logger log = LoggerFactory.getLogger(WebServiceConfiguration.class);

  @Autowired
  private WebServiceClient client;

  @PostConstruct
  public void test() {
    UserDetailsResponse userDetails =
        client.getUserDetails("lubos.krnac@gmail.com");
    log.info("User Details: " + userDetails.getFirstName() + " "
        + userDetails.getLastName());
  }

}
