package net.lkrnac.book.eiws.chapter03.ws.javaconfig.client;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter03.ws.javaconfig.model.UserDetailsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.client.core.WebServiceTemplate;

@ContextConfiguration(classes = WsClientConfiguration.class)
public class WsJavaConfigE2eITCase extends AbstractTestNGSpringContextTests {
  @Autowired
  private WebServiceClient webServiceClient;

  @Autowired
  private WebServiceTemplate webServiceTemplate;

  /**
   * Turned off because tomcat7-maven-plugin doesn't execute dependency war
   * without web.xml
   * 
   * @throws IOException
   */
  // @Test(groups = "maventests")
  public void testGetUserDetails() throws IOException {
    // GIVEN

    // WHEN
    UserDetailsResponse userDetails =
        webServiceClient.getUserDetails("lubos.krnac@gmail.com");

    // THEN
    assertEquals(userDetails.getFirstName(), "Lubos");
    assertEquals(userDetails.getLastName(), "Krnac");
  }
}
