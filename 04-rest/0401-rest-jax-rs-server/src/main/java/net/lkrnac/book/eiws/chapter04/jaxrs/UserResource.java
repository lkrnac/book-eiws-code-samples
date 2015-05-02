package net.lkrnac.book.eiws.chapter04.jaxrs;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.lkrnac.book.eiws.chapter04.UrlConstants;
import net.lkrnac.book.eiws.chapter04.model.User;
import net.lkrnac.book.eiws.chapter04.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path(UrlConstants.USERS_URL)
@Component
public class UserResource {
  private final UserService userService;

  @Autowired
  public UserResource(UserService userService) {
    super();
    this.userService = userService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Collection<User> getUsers() {
    return userService.getAllUsers();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getUser(@PathParam("id") int identifier) {
    return userService.getUser(identifier);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postUser(User user) throws URISyntaxException {
    int identifier = userService.addUser(user);
    URI uri = new URI(UrlConstants.USERS_URL + "/" + identifier);
    return Response.created(uri).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void putUser(@PathParam("id") int identifier, User user)
      throws URISyntaxException {
    userService.updateOrAddUser(identifier, user);
  }

  @DELETE
  @Path("/{id}")
  public Response deleteUser(@PathParam("id") int identifier) {
    userService.deleteUser(identifier);
    return Response.ok().build();
  }
}
