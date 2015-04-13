package net.lkrnac.book.eiws.chapter04.client;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withCreatedEntity;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.util.List;

import net.lkrnac.book.eiws.chapter04.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@WebAppConfiguration
@ContextConfiguration(classes = ClientConfiguration.class)
public class RestClientBootApplicationTest extends
    AbstractTestNGSpringContextTests {
  private static final String USER0_NAME = "User0";
  private static final String USER0_EMAIL = "user0@gmail.com";
  private static final String TEST_RECORD0 =
      "{\"identifier\": \"0\", \"email\": \"" + USER0_EMAIL
          + "\", \"name\": \"" + USER0_NAME + "\"}";
  private static final String TEST_RECORD1 =
      "{\"identifier\": \"1\", \"email\": \"user1@gmail.com\", \"name\": \"User1\"}";
  private static final String USERS_URL = "http://localhost:10404/users";

  private MockRestServiceServer mockServer;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private UsersClient usersClient;

  @BeforeMethod
  public void init() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void testPost() throws Exception {
    // GIVEN
    URI expectedUri = new URI(USERS_URL + "/0");
    //@formatter:off
    mockServer.expect(requestTo(USERS_URL))
      .andExpect(method(HttpMethod.POST))
      .andExpect(jsonPath("$.identifier", is(0)))
      .andExpect(jsonPath("$.email", is(USER0_EMAIL)))
      .andExpect(jsonPath("$.name", is(USER0_NAME)))
      .andRespond(withCreatedEntity(expectedUri));
    //@formatter:on

    User user = new User();
    user.setIdentifier(0);
    user.setName(USER0_NAME);
    user.setEmail(USER0_EMAIL);

    // WHEN
    URI location = usersClient.createUser(user);

    // THEN
    Assert.assertEquals(location, expectedUri);
    mockServer.verify();
  }

  @Test
  public void testSingleGet() throws Exception {
    // GIVEN
    //@formatter:off
    int testingIdentifier = 0;
    mockServer.expect(requestTo(USERS_URL + "/" + testingIdentifier))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(TEST_RECORD0, MediaType.APPLICATION_JSON));
    //@formatter:on

    // WHEN
    User user = usersClient.getUser(testingIdentifier);

    // THEN
    mockServer.verify();
    assertEquals(user.getIdentifier(), testingIdentifier);
    assertEquals(user.getName(), USER0_NAME);
    assertEquals(user.getEmail(), USER0_EMAIL);
  }

  @Test
  public void testMultiGet() throws Exception {
    // GIVEN
    //@formatter:off
    mockServer.expect(requestTo(USERS_URL))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess("[ " + TEST_RECORD0 + ", " + TEST_RECORD1 + "]", 
          MediaType.APPLICATION_JSON));
    //@formatter:on

    // WHEN
    List<User> users = usersClient.getUsers();

    // THEN
    mockServer.verify();
    assertEquals(users.get(0).getIdentifier(), 0);
    assertEquals(users.get(0).getName(), USER0_NAME);
    assertEquals(users.get(0).getEmail(), USER0_EMAIL);
    assertEquals(users.get(1).getIdentifier(), 1);
    assertEquals(users.get(1).getName(), "User1");
    assertEquals(users.get(1).getEmail(), "user1@gmail.com");
  }

  @Test
  public void testDeleteUser() throws Exception {
    // GIVEN
    //@formatter:off
    int testingIdentifier = 1;
    mockServer.expect(requestTo(USERS_URL + "/" + testingIdentifier))
      .andExpect(method(HttpMethod.DELETE))
      .andRespond(withSuccess());
    //@formatter:on

    // WHEN
    usersClient.deleteUser(testingIdentifier);

    // THEN
    mockServer.verify();
  }
}
