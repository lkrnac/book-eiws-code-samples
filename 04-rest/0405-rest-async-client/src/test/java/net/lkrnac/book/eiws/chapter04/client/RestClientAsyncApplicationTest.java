package net.lkrnac.book.eiws.chapter04.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import net.lkrnac.book.eiws.chapter04.model.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.AsyncRestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ClientConfiguration.class)
public class RestClientAsyncApplicationTest extends
    AbstractTestNGSpringContextTests {
  private static final String USER0_NAME = "User0";
  private static final String USER0_EMAIL = "user0@gmail.com";
  private static final String TEST_RECORD0 = "{\"email\": \"" + USER0_EMAIL
      + "\", \"name\": \"" + USER0_NAME + "\"}";

  private static final String USERS_URL = "http://localhost:10405/users";

  @Autowired
  private UserInfoService userInfoService;

  @Autowired
  private AsyncRestTemplate asyncRestTemplate;

  private MockRestServiceServer mockServer;

  @BeforeClass
  public void init() {
    mockServer = MockRestServiceServer.createServer(asyncRestTemplate);
  }

  @Test
  public void test() throws InterruptedException {
    // GIVEN
    //@formatter:off
    int testingIdentifier = 0;
    mockServer.expect(requestTo(USERS_URL + "/" + testingIdentifier))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(TEST_RECORD0, MediaType.APPLICATION_JSON));
    //@formatter:on

    Collection<String> expectedUserActions =
        Arrays.asList(new String[] { "dummy action 1", "dummy action 2", });

    // WHEN
    UserInfo userInfo = userInfoService.getUserInfo(testingIdentifier);

    Thread.sleep(100);

    // THEN
    mockServer.verify();
    assertEquals(userInfo.getUser().getName(), USER0_NAME);
    assertEquals(userInfo.getUser().getEmail(), USER0_EMAIL);
    assertEquals(userInfo.getUserActions(), expectedUserActions);
  }
}
