package net.lkrnac.book.eiws.chapter03.ws.xmpp.server;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmpp.eiws_blah.UserDetailsResponse;
import xmpp.eiws_blah.UserRequest;

@Endpoint
public class UserEndpoint {
  @PayloadRoot(namespace = ServerConfiguration.NAMESPACE, localPart = "UserRequest")
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
