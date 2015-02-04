package net.lkrnac.book.eiws.chapter03.ws.xmlconfig.client;

import javax.annotation.PostConstruct;

import net.lkrnac.book.eiws.chapter03.ws.xmlconfig.model.UserDetailsResponse;
import net.lkrnac.book.eiws.chapter03.ws.xmlconfig.model.UserRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
@EnableAutoConfiguration
public class WebServiceClient {
  private static final Logger log = LoggerFactory
      .getLogger(WebServiceClient.class);

  private static final String URL =
      "http://localhost:10301/0301-ws-xmlconfig-service/getUserDetails";
  private WebServiceTemplate webServiceTemplate;

  @Autowired
  public WebServiceClient(WebServiceTemplate webServiceTemplate) {
    this.webServiceTemplate = webServiceTemplate;
  }

  public UserDetailsResponse getUserDetails(String email) {
    UserRequest request = new UserRequest();
    request.setEmail(email);

    UserDetailsResponse userDetails =
        (UserDetailsResponse) webServiceTemplate.marshalSendAndReceive(URL,
            request);
    return userDetails;
  }

  @PostConstruct
  public void test() {
    UserDetailsResponse userDetails =
        this.getUserDetails("lubos.krnac@gmail.com");
    log.error("User Details: " + userDetails.getFirstName() + " "
        + userDetails.getLastName());
  }
}
