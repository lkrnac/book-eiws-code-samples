package net.lkrnac.book.eiws.chapter03.ws.javaconfig.server;

import net.lkrnac.book.eiws.chapter03.ws.javaconfig.model.UserDetailsType;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
  @PayloadRoot(namespace = ServerConfiguration.NAMESPACE, localPart = "getUserDetails")
  @ResponsePayload
  public UserDetailsType getCountry(@RequestPayload String userEmail) {
    UserDetailsType userDetails = null;
    if ("lubos.krnac@gmail.com".equals(userEmail)) {
      userDetails = new UserDetailsType();
      userDetails.setFirstName("Lubos");
      userDetails.setLastName("Krnac");
    }
    return userDetails;
  }
}
