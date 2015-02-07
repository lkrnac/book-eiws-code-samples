package net.lkrnac.book.eiws.chapter03.ws.javaconfig.client;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter03.ws.javaconfig.server.ServerConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseActions;
import org.springframework.ws.test.server.ResponseMatchers;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServerConfiguration.class)
public class WsJavaConfigServerITest extends AbstractTestNGSpringContextTests {
  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void testGetUserDetails() throws IOException {
    // GIVEN
    MockWebServiceClient wsClient =
        MockWebServiceClient.createClient(applicationContext);

    RequestCreator requestCreator =
        RequestCreators.withPayload(new ClassPathResource("testRequest.xml"));

    // WHEN
    ResponseActions response = wsClient.sendRequest(requestCreator);

    // THEN
    response.andExpect(ResponseMatchers.noFault()).andExpect(
        ResponseMatchers.payload(new ClassPathResource("testResponse.xml")));
  }
}
