package net.lkrnac.book.eiws.chapter03.ws.xmlconfig.client;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter03.ws.xmlconfig.config.SimpleLogger;
import net.lkrnac.book.eiws.chapter03.ws.xmlconfig.model.UserDetailsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.ws.test.client.RequestMatchers;
import org.springframework.ws.test.client.ResponseCreator;
import org.springframework.ws.test.client.ResponseCreators;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:ws-client-config.xml")
@DirtiesContext
public class WsInterceptorClientITest extends AbstractTestNGSpringContextTests {
  @Autowired
  private WebServiceClient webServiceClient;

  @Autowired
  private WebServiceTemplate webServiceTemplate;

  @Autowired
  private SimpleLogger logger;

  @BeforeMethod
  public void resetMocks() {
    reset(logger);
  }

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
    verify(logger).log("Client handleRequest");
    verify(logger).log("Client handleResponse");
    verify(logger).log("Client afterCompletion");
    verify(logger, times(0)).log("Client handleFault");
  }
}
