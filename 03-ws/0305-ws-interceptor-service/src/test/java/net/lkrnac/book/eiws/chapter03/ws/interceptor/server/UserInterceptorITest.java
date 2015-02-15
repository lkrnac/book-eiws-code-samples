package net.lkrnac.book.eiws.chapter03.ws.interceptor.server;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServerConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserInterceptorITest extends AbstractTestNGSpringContextTests {
  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private SimpleLogger logger;

  @Test
  public void testGetUserDetails_Success() throws IOException {
    // GIVEN
    MockWebServiceClient wsClient =
        MockWebServiceClient.createClient(applicationContext);

    RequestCreator requestCreator =
        RequestCreators.withPayload(new ClassPathResource(
            "testRequest-success.xml"));

    // WHEN
    wsClient.sendRequest(requestCreator);

    // THEN
    verify(logger).log("Endpoint handleRequest");
    verify(logger).log("Endpoint handleResponse");
    verify(logger).log("Endpoint afterCompletion");
    verify(logger, times(0)).log("Endpoint handleFault");
  }

  @Test
  public void testGetUserDetails_Fail() throws IOException {
    // GIVEN
    MockWebServiceClient wsClient =
        MockWebServiceClient.createClient(applicationContext);

    RequestCreator requestCreator =
        RequestCreators.withPayload(new ClassPathResource(
            "testRequest-fail.xml"));

    // WHEN
    wsClient.sendRequest(requestCreator);

    // THEN
    verify(logger).log("Endpoint handleRequest");
    verify(logger).log("Endpoint handleFault");
    verify(logger).log("Endpoint afterCompletion");
    verify(logger, times(0)).log("Endpoint handleResponse");
  }
}
