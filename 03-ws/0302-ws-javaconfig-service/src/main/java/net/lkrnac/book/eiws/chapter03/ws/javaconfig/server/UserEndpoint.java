package net.lkrnac.book.eiws.chapter03.ws.javaconfig.server;

import localhost._10302._0302_ws_javaconfig_service.UserDetailsResponse;
import localhost._10302._0302_ws_javaconfig_service.UserRequest;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

@Endpoint
public class UserEndpoint {
  // @PayloadRoot(namespace =
  // "http://localhost:10302/0302-ws-javaconfig-service", localPart =
  // "UserRequest")
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
