package net.lkrnac.book.eiws.chapter04.boot;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = RestClientBootApplication.class)
@WebAppConfiguration
public class RestClientBootApplicationTest extends
    AbstractTestNGSpringContextTests {
  private static final String DUBLIN = "Dublin";

  private static final String BRATISLAVA = "Bratislava";

  private static final String FLIGHTS_URL = "/flights";

  private MockRestServiceServer mockServer;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private FlightsClient flightsClient;

  @BeforeClass
  public void init() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void testPost() throws Exception {
    // GIVEN
    //@formatter:off
    mockServer.expect(requestTo(FLIGHTS_URL))
      .andExpect(method(HttpMethod.POST))
      .andExpect(jsonPath("$.identifier", is(1)))
      .andExpect(jsonPath("$.origin", is(BRATISLAVA)))
      .andExpect(jsonPath("$.destination", is(DUBLIN)))
      .andRespond(withSuccess());
    //@formatter:on

    Flight flight = new Flight();
    flight.setIdentifier(1);
    flight.setOrigin(BRATISLAVA);
    flight.setDestination(DUBLIN);

    // WHEN
    flightsClient.bookFlight(flight);

    // THEN
    mockServer.verify();
  }

  @Test
  public void testSingleGet() throws Exception {
  }

  @Test
  public void testMultiGet() throws Exception {
  }

  @Test
  public void testDeleteFlight() throws Exception {
  }
}
