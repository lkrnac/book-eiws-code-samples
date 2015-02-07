package net.lkrnac.book.eiws.chapter03.ws.boot.server;

import localhost._10303._0303_ws_boot_service.UserDetailsResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
  @PayloadRoot(namespace = ServerConfiguration.NAMESPACE, localPart = "UserRequest")
  @ResponsePayload
  public UserDetailsResponse getCountry(@RequestPayload String userEmail) {
    UserDetailsResponse userDetails = null;
    if ("lubos.krnac@gmail.com".equals(userEmail)) {
      userDetails = new UserDetailsResponse();
      userDetails.setFirstName("Lubos");
      userDetails.setLastName("Krnac");
    }
    return userDetails;
  }
}
