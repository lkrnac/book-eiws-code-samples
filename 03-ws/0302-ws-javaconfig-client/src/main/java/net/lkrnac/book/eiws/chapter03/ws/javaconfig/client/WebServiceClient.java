package net.lkrnac.book.eiws.chapter03.ws.javaconfig.client;

import net.lkrnac.book.eiws.chapter03.ws.javaconfig.model.UserDetailsResponse;
import net.lkrnac.book.eiws.chapter03.ws.javaconfig.model.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;

@Component
public class WebServiceClient {
  private WebServiceTemplate webServiceTemplate;

  private static final WebServiceMessageCallback messageCallback =
      new WebServiceMessageCallback() {
        public void doWithMessage(WebServiceMessage message) {
          ((SoapMessage) message).setSoapAction("getUserDetails");
        }
      };

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
