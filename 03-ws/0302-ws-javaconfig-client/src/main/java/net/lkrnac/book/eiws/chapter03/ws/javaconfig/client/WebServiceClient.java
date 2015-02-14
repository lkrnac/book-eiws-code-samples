package net.lkrnac.book.eiws.chapter03.ws.javaconfig.client;

import net.lkrnac.book.eiws.chapter03.ws.javaconfig.model.UserDetailsResponse;
import net.lkrnac.book.eiws.chapter03.ws.javaconfig.model.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Component
public class WebServiceClient {
  private WebServiceTemplate webServiceTemplate;

  private static final SoapActionCallback messageCallback =
      new SoapActionCallback("getUserDetails");

  @Autowired
  public WebServiceClient(WebServiceTemplate webServiceTemplate) {
    this.webServiceTemplate = webServiceTemplate;
  }

  public UserDetailsResponse getUserDetails(String email) {
    UserRequest request = new UserRequest();
    request.setEmail(email);

    UserDetailsResponse userDetails =
        (UserDetailsResponse) webServiceTemplate.marshalSendAndReceive(request,
            messageCallback);
    return userDetails;
  }
}
