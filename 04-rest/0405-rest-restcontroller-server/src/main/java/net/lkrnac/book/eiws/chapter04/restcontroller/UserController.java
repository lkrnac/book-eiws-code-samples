package net.lkrnac.book.eiws.chapter04.restcontroller;

import java.util.Collection;

import net.lkrnac.book.eiws.chapter04.UrlConstants;
import net.lkrnac.book.eiws.chapter04.model.User;
import net.lkrnac.book.eiws.chapter04.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(UrlConstants.USERS_URL)
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    super();
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public Collection<User> getUsers() {
    return userService.getAllUsers();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User getUser(@PathVariable("id") int identifier) {
    return userService.getUser(identifier);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> postUser(@RequestBody User user,
      UriComponentsBuilder uriBuilder) {
    int identifier = userService.addUser(user);

    HttpHeaders httpHeaders = new HttpHeaders();
    String uri = UrlConstants.USERS_URL + "/{id}";
    UriComponents uriComponents =
        uriBuilder.path(uri).buildAndExpand(identifier);
    httpHeaders.setLocation(uriComponents.toUri());
    return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public void putUser(@PathVariable("id") int identifier, @RequestBody User user) {
    userService.updateOrAddUser(identifier, user);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable("id") int identifier) {
    userService.deleteUser(identifier);
  }

  @ExceptionHandler(UnsupportedOperationException.class)
  public ResponseEntity<String> handleUnsupportedOperation(
      UnsupportedOperationException uoe) {
    return new ResponseEntity<String>(uoe.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
