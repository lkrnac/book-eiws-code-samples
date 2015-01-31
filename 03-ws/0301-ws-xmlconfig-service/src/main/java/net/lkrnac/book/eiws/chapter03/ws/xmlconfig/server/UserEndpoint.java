package net.lkrnac.book.eiws.chapter03.ws.xmlconfig.server;

import localhost._10301._0301_ws_xmlconfig_service.UserDetailsResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
// @Address("http://localhost:8080/StockService")
public class UserEndpoint {
  // @PayloadRoot(namespace =
  // "http://lkrnac.net/book/eiws/chapter03/ws/xmlconfig/model", localPart =
  // "getUserDetails")
  @PayloadRoot(namespace = "http://localhost:10301/0301-ws-xmlconfig-service/", localPart = "getUserDetails")
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
