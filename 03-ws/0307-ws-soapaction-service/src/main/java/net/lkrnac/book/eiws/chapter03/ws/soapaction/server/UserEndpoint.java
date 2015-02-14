package net.lkrnac.book.eiws.chapter03.ws.soapaction.server;

import localhost._10307._0307_ws_soapaction_service.UserDetailsResponse;
import localhost._10307._0307_ws_soapaction_service.UserRequest;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

@Endpoint
public class UserEndpoint {
  @SoapAction("getUserDetails")
  @ResponsePayload
  public UserDetailsResponse getUserDetails(
      @RequestPayload UserRequest userRequest) {
    UserDetailsResponse userDetails = null;
    if ("lubos.krnac@gmail.com".equals(userRequest.getEmail())) {
      userDetails = new UserDetailsResponse();
      userDetails.setFirstName("Lubos");
      userDetails.setLastName("Krnac");
    }
    return userDetails;
  }
}
