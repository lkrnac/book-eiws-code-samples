package net.lkrnac.book.eiws.chapter04.parameters;

import java.util.Collection;
import java.util.List;

import net.lkrnac.book.eiws.chapter04.UrlConstants;
import net.lkrnac.book.eiws.chapter04.model.User;
import net.lkrnac.book.eiws.chapter04.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public User getUser(@PathVariable("id") int identifier) {
    return userService.getUser(identifier);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "version=1")
  public ResponseEntity<String> postUser(@RequestBody User user,
      UriComponentsBuilder uriBuilder,
      @RequestHeader(required = false) String version) {
    if ("1".equals(version)) {
      int identifier = userService.addUser(user);

      HttpHeaders httpHeaders = new HttpHeaders();
      String uri = UrlConstants.USERS_URL + "/{id}";
      UriComponents uriComponents =
          uriBuilder.path(uri).buildAndExpand(identifier);
      httpHeaders.setLocation(uriComponents.toUri());
      return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    } else {
      return ResponseEntity.badRequest().body("Expected version is 1!");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<String> putUser(@PathVariable("id") int identifier,
      RequestEntity<User> request) {
    List<String> versions = request.getHeaders().get("version");
    boolean versionIsCorrect = versions != null && "1".equals(versions.get(0));
    if (versionIsCorrect) {
      userService.updateOrAddUser(identifier, request.getBody());
      return ResponseEntity.ok("");
    } else {
      return ResponseEntity.badRequest().body("Expected version is 1!");
    }
  }

  @RequestMapping(method = RequestMethod.GET, params = "lowerId", produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<User> getUsersInterval(@RequestParam int lowerId,
      @RequestParam("upperId") int upId) {
    return userService.getUsersInterval(lowerId, upId);
  }

  @ExceptionHandler(UnsupportedOperationException.class)
  public ResponseEntity<String> handleUnsupportedOperation(
      UnsupportedOperationException uoe) {
    return new ResponseEntity<String>(uoe.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
