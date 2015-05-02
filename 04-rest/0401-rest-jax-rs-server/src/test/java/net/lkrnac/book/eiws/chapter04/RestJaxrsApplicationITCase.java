package net.lkrnac.book.eiws.chapter04;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.lkrnac.book.eiws.BiFunctionRetryHandler;
import net.lkrnac.book.eiws.ProcessExecutor;
import net.lkrnac.book.eiws.chapter04.model.User;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = RestJaxrsApplication.class)
@WebAppConfiguration
public class RestJaxrsApplicationITCase extends
    AbstractTestNGSpringContextTests {
  private static final String USER_UPDATED = "User Updated";
  private static final String USER_UPDATED_GMAIL_COM = "user.updated@gmail.com";
  private static final String MAVENTESTS = "maventests";
  private static final String FULL_USERS_URL = "http://localhost:10401/users";
  private static final int RETRY_TIMEOUT = 10000;

  private final RestTemplate restTemplate = new RestTemplate();
  private Process process;

  @BeforeClass
  public void startApp() throws IOException, URISyntaxException {
    process =
        new ProcessExecutor().execute("", "0401-rest-jax-rs-server-exec.jar");

    BiFunctionRetryHandler<URI, Class<User[]>, User[]> retryHandler =
        new BiFunctionRetryHandler<>();

    retryHandler.retry(restTemplate::getForObject, new URI(FULL_USERS_URL),
        User[].class, RETRY_TIMEOUT);
  }

  @AfterClass
  public void stopApp() throws InterruptedException {
    process.destroyForcibly();
    process.waitFor();
  }

  @Test(groups = MAVENTESTS)
  public void testPost() {
    // GIVEN
    int testingIdentifier = 0;
    User user = createTestingRecord(testingIdentifier);

    // WHEN
    ResponseEntity<String> response =
        restTemplate.postForEntity(FULL_USERS_URL, user, String.class);

    // THEN
    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    assertEquals(response.getHeaders().get("Location").get(0), FULL_USERS_URL
        + "/" + testingIdentifier);
  }

  @Test(groups = MAVENTESTS, dependsOnMethods = "testPost")
  public void testSingleGet() {
    // GIVEN
    int testingIdentifier = 1;
    User expectedUser = createTestingRecord(testingIdentifier);
    restTemplate.postForEntity(FULL_USERS_URL, expectedUser, String.class);
    String url = FULL_USERS_URL + "/" + testingIdentifier;

    // WHEN
    User actualUser = restTemplate.getForObject(url, User.class);

    // THEN
    assertEquals(actualUser.getEmail(), expectedUser.getEmail());
    assertEquals(actualUser.getName(), expectedUser.getName());
  }

  @Test(groups = MAVENTESTS, dependsOnMethods = "testSingleGet")
  public void testSingleGetNoContent() {
    // GIVEN

    // WHEN
    User actualUser =
        restTemplate.getForObject(FULL_USERS_URL + "/3", User.class);

    // THEN
    assertNull(actualUser);
  }

  @Test(groups = MAVENTESTS, dependsOnMethods = "testSingleGetNoContent")
  public void testMultiGet() {
    // GIVEN
    restTemplate.delete(FULL_USERS_URL + "/" + 0);
    restTemplate.delete(FULL_USERS_URL + "/" + 1);

    int testingIdentifier1 = 2;
    User expectedUser1 = createTestingRecord(testingIdentifier1);
    restTemplate.postForEntity(FULL_USERS_URL, expectedUser1, String.class);

    int testingIdentifier2 = 3;
    User expectedUser2 = createTestingRecord(testingIdentifier2);
    restTemplate.postForEntity(FULL_USERS_URL, expectedUser2, String.class);

    // WHEN
    User[] actualUsers =
        restTemplate.getForObject(FULL_USERS_URL, User[].class);

    // THEN
    assertEquals(actualUsers[0].getEmail(), expectedUser1.getEmail());
    assertEquals(actualUsers[0].getName(), expectedUser1.getName());
    assertEquals(actualUsers[1].getEmail(), expectedUser2.getEmail());
    assertEquals(actualUsers[1].getName(), expectedUser2.getName());
  }

  @Test(groups = MAVENTESTS, dependsOnMethods = "testMultiGet")
  public void testPutUser() {
    // GIVEN
    int testingIdentifier = 1;
    User user = new User();
    user.setEmail(USER_UPDATED_GMAIL_COM);
    user.setName(USER_UPDATED);

    String url = FULL_USERS_URL + "/" + testingIdentifier;

    // WHEN
    restTemplate.put(url, user);

    // THEN
    User actualUser = restTemplate.getForObject(url, User.class);
    assertEquals(actualUser.getEmail(), USER_UPDATED_GMAIL_COM);
    assertEquals(actualUser.getName(), USER_UPDATED);

  }

  @Test(groups = MAVENTESTS, dependsOnMethods = "testPutUser")
  public void testDeleteUser() {
    // GIVEN
    int testinIdentifier = 1;
    User expectedUser = createTestingRecord(testinIdentifier);
    restTemplate.postForEntity(FULL_USERS_URL, expectedUser, String.class);
    String url = FULL_USERS_URL + "/" + testinIdentifier;

    // WHEN
    restTemplate.delete(url);

    // THEN
    User actualUser = restTemplate.getForObject(url, User.class);
    assertNull(actualUser);
  }

  private User createTestingRecord(int idx) {
    User user = new User();
    user.setEmail("user" + idx + "@gmail.com");
    user.setName("User" + idx);
    return user;
  }

  @Test(groups = MAVENTESTS, expectedExceptions = HttpClientErrorException.class)
  public void testClientError() {
    // GIVEN
    String url = FULL_USERS_URL + "/-1";

    // WHEN
    restTemplate.getForObject(url, User.class);

    // THEN - expected exception in test annotation
  }
}
