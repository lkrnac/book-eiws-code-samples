package net.lkrnac.book.eiws.chapter04.client;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import net.lkrnac.book.eiws.chapter04.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsersClient {
  private static final String URL = "http://localhost:10404/users";
  private RestTemplate restTemplate;

  @Autowired
  public UsersClient(RestTemplate restTemplate) {
    super();
    this.restTemplate = restTemplate;
  }

  public URI createUser(User user) {
    return restTemplate.postForLocation(URL, user);
  }

  public User getUser(int identifier) {
    return restTemplate.getForObject(URL + "/" + identifier, User.class);
  }

  public List<User> getUsers() {
    User[] usersArray = restTemplate.getForObject(URL, User[].class);
    return Arrays.asList(usersArray);
  }

  public void updateOrCreateUser(int identifier, User user) {
    restTemplate.put(URL + "/" + identifier, user);
  }

  public void deleteUser(int identifier) {
    restTemplate.delete(URL + "/" + identifier);
  }

}
