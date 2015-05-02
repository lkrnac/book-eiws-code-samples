package net.lkrnac.book.eiws.chapter04;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import net.lkrnac.book.eiws.chapter04.javaconfig.UserController;
import net.lkrnac.book.eiws.chapter04.model.User;
import net.lkrnac.book.eiws.chapter04.service.UserService;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.Test;

public class UserControllerTest {
  private static final int TESTING_ID = 0;
  private static final String FULL_USER_URL = "http://localhost:10403/users";

  private static User createTestUser(int identifier) {
    User user = new User();
    user.setEmail("user" + identifier + "@gmail.com");
    user.setName("User" + identifier);
    return user;
  }

  private static String createTestRecord(int identifier) {
    String testingRecordString =
        "{\"email\": \"user%d@gmail.com\", \"name\": \"User%d\"}";
    return String.format(testingRecordString, identifier, identifier,
        identifier);
  }

  @Test
  public void testPost() throws Exception {
    // GIVEN
    UserService userRepository = Mockito.mock(UserService.class);
    UserController userController = new UserController(userRepository);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    User testingUser = createTestUser(TESTING_ID);
    Mockito.when(userRepository.addUser(testingUser)).thenReturn(TESTING_ID);

    // WHEN
    // @formatter:off
    MvcResult mvcResult = mockMvc.perform(post(FULL_USER_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(createTestRecord(0)))
        .andReturn();
    // @formatter:on

    // THEN
    int httpStatus = mvcResult.getResponse().getStatus();
    assertEquals(httpStatus, HttpStatus.CREATED.value());

    Mockito.verify(userRepository).addUser(testingUser);
    Mockito.verifyNoMoreInteractions(userRepository);
  }

  @Test
  public void testSingleGet() throws Exception {
    // @formatter:off
    // GIVEN
    UserService userRepository = Mockito.mock(UserService.class);
    UserController userController = new UserController(userRepository);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    User testingUser = createTestUser(TESTING_ID);
    Mockito.when(userRepository.getUser(TESTING_ID)).thenReturn(testingUser);

    // WHEN
    mockMvc.perform(get(FULL_USER_URL + "/{id}", 0)
        .accept(MediaType.APPLICATION_JSON)
      )

    // THEN
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.email").value("user0@gmail.com"))
      .andExpect(jsonPath("$.name").value("User0"));
    // @formatter:off
    
  }

  @Test
  public void testMultiGet() throws Exception {
    // @formatter:off
    // GIVEN
    UserService userRepository = Mockito.mock(UserService.class);
    UserController userController = new UserController(userRepository);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    Collection<User> testingUsers = new ArrayList<>(); 
    testingUsers.add(createTestUser(TESTING_ID));
    testingUsers.add(createTestUser(1));
    Mockito.when(userRepository.getAllUsers()).thenReturn(testingUsers);
    
    // WHEN
    mockMvc.perform(get(FULL_USER_URL).accept(MediaType.APPLICATION_JSON))

    // THEN
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].email").value("user0@gmail.com"))
      .andExpect(jsonPath("$[0].name").value("User0"))
      .andExpect(jsonPath("$[1].email").value("user1@gmail.com"))
      .andExpect(jsonPath("$[1].name").value("User1"));
    // @formatter:off
  }
  
  @Test
  public void testDeleteUser() throws Exception{
    //GIVEN
    UserService userRepository = Mockito.mock(UserService.class);
    UserController userController = new UserController(userRepository);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    //WHEN
    mockMvc.perform(delete(FULL_USER_URL + "/{id}", 0));
    
    //THEN
    Mockito.verify(userRepository).deleteUser(TESTING_ID);
    Mockito.verifyNoMoreInteractions(userRepository);
  }
}
