package net.lkrnac.book.eiws.chapter04;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:rest-service-config.xml")
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RestXmlConfigApplicationTest extends
    AbstractTestNGSpringContextTests {

  private static final String FULL_USER_URL = "http://localhost:10402/users";
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeMethod
  public void init() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testPost() throws Exception {
    // GIVEN: TEST_RECORD1

    // WHEN
    // @formatter:off
    mockMvc.perform(post(FULL_USER_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(createTestRecord(0)))
         
    // THEN
      .andExpect(status().isCreated());
    // @formatter:on
  }

  @Test
  public void testSingleGet() throws Exception {
    // @formatter:off
    // GIVEN
    mockMvc.perform(post(FULL_USER_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(createTestRecord(0)));

    // WHEN
    mockMvc.perform(get(FULL_USER_URL + "/{id}", 0)
        .accept(MediaType.APPLICATION_JSON)
      )

    // THEN
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.email").value("user0@gmail.com"))
      .andExpect(jsonPath("$.name").value("User0"));
    // @formatter:off
  }

  @Test
  public void testMultiGet() throws Exception {
    // @formatter:off
    // GIVEN
    mockMvc.perform(post(FULL_USER_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(createTestRecord(0)));
    mockMvc.perform(post(FULL_USER_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(createTestRecord(1)));

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
  public void testPut() throws Exception {
    // GIVEN: TEST_RECORD1

    // WHEN
    // @formatter:off
    mockMvc.perform(put(FULL_USER_URL + "/0")
        .contentType(MediaType.APPLICATION_JSON)
        .content(createTestRecord(0)))
         
    // THEN
      .andExpect(status().isOk());
    // @formatter:on  
  }

  @Test
  public void testDeleteUser() throws Exception {
    //@formatter:off 
    //GIVEN
    mockMvc.perform(post(FULL_USER_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(createTestRecord(0)));
    
    //WHEN
    mockMvc.perform(delete(FULL_USER_URL + "/{id}", 0));
    
    //THEN
    mockMvc.perform(get(FULL_USER_URL + "/{id}", 0)
        .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(content().string(""));
    //@formatter:on
  }

  @Test
  public void testClientError() throws Exception {
    // @formatter:off
    // GIVEN

    // WHEN
    mockMvc.perform(get(FULL_USER_URL + "/{id}", -1)
        .accept(MediaType.APPLICATION_JSON)
      )

    // THEN
      .andExpect(status().isBadRequest())
      .andExpect(content().string("Identifier -1 is not supported."));
    // @formatter:off
  }

  private static String createTestRecord(int identifier) {
    String testingRecordString =
        "{\"email\": \"user%d@gmail.com\", \"name\": \"User%d\"}";
    return String.format(testingRecordString, identifier, identifier,
        identifier); 
  }
}
