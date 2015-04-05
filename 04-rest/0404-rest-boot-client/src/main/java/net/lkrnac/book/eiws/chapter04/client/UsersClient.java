package net.lkrnac.book.eiws.chapter04.client;

import java.util.Arrays;
import java.util.List;

import net.lkrnac.book.eiws.chapter04.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsersClient {
  private RestTemplate restTemplate;
  private String usersEndpointUrl;

  @Autowired
  public UsersClient(
      @Value("${user.server.hostname}") String userServerHostname,
      RestTemplate restTemplate) {
    super();
    this.usersEndpointUrl = userServerHostname + "/users";
    this.restTemplate = restTemplate;
  }

  public void createUser(User user) {
    restTemplate.postForObject(usersEndpointUrl, user, Object.class);
  }

  public User getUser(int identifier) {
    return restTemplate.getForObject(usersEndpointUrl + "/" + identifier,
        User.class);
  }

  public List<User> getUsers() {
    User[] usersArray =
        restTemplate.getForObject(usersEndpointUrl, User[].class);
    return Arrays.asList(usersArray);
  }

  public void deleteUser(int identifier) {
    restTemplate.delete(usersEndpointUrl + "/" + identifier);
  }
}
