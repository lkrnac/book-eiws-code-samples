package net.lkrnac.book.eiws.chapter04.restcontroller;

import java.util.Collection;

import net.lkrnac.book.eiws.chapter04.UrlConstants;
import net.lkrnac.book.eiws.chapter04.model.User;
import net.lkrnac.book.eiws.chapter04.persistence.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping(UrlConstants.USERS_URL)
public class UserController {
  private final UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    super();
    this.userRepository = userRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public Collection<User> getUsers() {
    return userRepository.getAllUsers();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public User getUser(@PathVariable("id") int identifier) {
    return userRepository.getUser(identifier);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> postUser(@RequestBody User user,
      UriComponentsBuilder uriBuilder) {
    int identifier = userRepository.addUser(user);

    HttpHeaders httpHeaders = new HttpHeaders();
    String uri = UrlConstants.USERS_URL + "/{id}";
    UriComponents uriComponents =
        uriBuilder.path(uri).buildAndExpand(identifier);
    httpHeaders.setLocation(uriComponents.toUri());
    return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable("id") int identifier) {
    userRepository.deleteUser(identifier);
  }
}
