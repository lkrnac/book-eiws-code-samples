package net.lkrnac.book.eiws.chapter03.ws.boot.client;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter03.ws.boot.model.UserDetailsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.ws.test.client.RequestMatchers;
import org.springframework.ws.test.client.ResponseCreator;
import org.springframework.ws.test.client.ResponseCreators;
import org.testng.annotations.Test;

@ContextConfiguration(classes = WsBootClientConfiguration.class)
public class WsXmlConfigClientITest extends AbstractTestNGSpringContextTests {
  @Autowired
  private WebServiceClient webServiceClient;

  @Autowired
  private WebServiceTemplate webServiceTemplate;

  @Test
  public void testGetUserDetails() throws IOException {
    // GIVEN
    MockWebServiceServer mockWsServer =
        MockWebServiceServer.createServer(webServiceTemplate);

    ResponseCreator responseCreator =
        ResponseCreators.withPayload(new ClassPathResource("testResponse.xml"));

    mockWsServer.expect(
        RequestMatchers.payload(new ClassPathResource("testRequest.xml")))
        .andRespond(responseCreator);

    // WHEN
    UserDetailsResponse userDetails =
        webServiceClient.getUserDetails("lubos.krnac@gmail.com");

    // THEN
    assertEquals(userDetails.getFirstName(), "Lubos");
    assertEquals(userDetails.getLastName(), "Krnac");
    mockWsServer.verify();
  }
}
