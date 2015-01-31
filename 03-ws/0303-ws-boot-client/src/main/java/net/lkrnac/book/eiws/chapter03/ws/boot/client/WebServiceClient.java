package net.lkrnac.book.eiws.chapter03.ws.boot.client;

import net.lkrnac.book.eiws.chapter03.ws.boot.model.UserDetailsResponse;
import net.lkrnac.book.eiws.chapter03.ws.boot.model.UserRequest;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class WebServiceClient extends WebServiceGatewaySupport {
  private static final String URL =
      "http://localhost:10303/0303-ws-boot-service/getUserDetails";

  public UserDetailsResponse getUserDetails(String email) {
    UserRequest request = new UserRequest();
    request.setEmail(email);

    UserDetailsResponse userDetails =
        (UserDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(
            URL, request);
    return userDetails;
  }
}
