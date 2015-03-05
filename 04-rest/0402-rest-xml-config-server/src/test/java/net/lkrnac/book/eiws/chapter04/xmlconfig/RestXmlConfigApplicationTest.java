package net.lkrnac.book.eiws.chapter04.xmlconfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:rest-service-config.xml")
@WebAppConfiguration
public class RestXmlConfigApplicationTest extends
    AbstractTestNGSpringContextTests {
  private static final String TEST_RECORD1 =
      "{\"identifier\": \"1\", \"origin\": \"Bratislava\", \"destination\": \"Dublin\"}";
  private static final String TEST_RECORD2 =
      "{\"identifier\": \"2\", \"origin\": \"Prague\", \"destination\": \"Paris\"}";

  private static final String FLIGHT_URL = "/flight";
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeClass
  public void init() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testPost() throws Exception {
    // GIVEN: TEST_RECORD1

    // WHEN
    // @formatter:off
    mockMvc.perform(post(FLIGHT_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(TEST_RECORD1))
         
    // THEN
      .andExpect(status().isCreated());
    // @formatter:on
  }

  @Test
  public void testSingleGet() throws Exception {
    // @formatter:off
    // GIVEN
    mockMvc.perform(post(FLIGHT_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(TEST_RECORD1));

    // WHEN
    mockMvc.perform(get(FLIGHT_URL + "/{id}", 1)
        .accept(MediaType.APPLICATION_JSON)
      )

    // THEN
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.identifier").value(1))
      .andExpect(jsonPath("$.origin").value("Bratislava"))
      .andExpect(jsonPath("$.destination").value("Dublin"));
    // @formatter:off
  }

  @Test
  public void testMultiGet() throws Exception {
    // @formatter:off
    // GIVEN
    mockMvc.perform(post(FLIGHT_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(TEST_RECORD1));
    mockMvc.perform(post(FLIGHT_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(TEST_RECORD2));

    // WHEN1
    mockMvc.perform(get(FLIGHT_URL).accept(MediaType.APPLICATION_JSON))

    // THEN
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].identifier").value(1))
      .andExpect(jsonPath("$[0].origin").value("Bratislava"))
      .andExpect(jsonPath("$[0].destination").value("Dublin"))
      .andExpect(jsonPath("$[1].identifier").value(2))
      .andExpect(jsonPath("$[1].origin").value("Prague"))
      .andExpect(jsonPath("$[1].destination").value("Paris"));
    // @formatter:off
  }
  
  @Test
  public void testDeleteFlight() throws Exception{
    //GIVEN
    mockMvc.perform(post(FLIGHT_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(TEST_RECORD1));
    
    //WHEN
    mockMvc.perform(delete(FLIGHT_URL + "/{id}", 1));
    
    //THEN
    mockMvc.perform(get(FLIGHT_URL + "/{id}", 1)
        .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(content().string(""));
  }
}
