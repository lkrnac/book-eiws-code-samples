package net.lkrnac.book.eiws.chapter08;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter08.model.User;
import net.lkrnac.book.eiws.chapter08.out.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

@WebIntegrationTest("server.port:0")
@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String LOCALHOST = "http://localhost:";

  @Value("${local.server.port}")
  private int serverPort;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void testSi() throws InterruptedException, IOException {
    // GIVEN
    RestTemplate restTemplate = new RestTemplate();
    String url = LOCALHOST + serverPort;
    User user = new User();
    user.setEmail("lubos.krnac@gmail.com");
    user.setName("Lubos Krnac");

    // WHEN
    boolean result = restTemplate.postForObject(url, user, Boolean.class);

    // THEN
    TestUserRepository testUserService = (TestUserRepository) userRepository;
    Assert.assertEquals(testUserService.getUser(), user);
    Assert.assertEquals(result, true);
  }
}
