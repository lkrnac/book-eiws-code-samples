package net.lkrnac.book.eiws.chapter04.boot;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = RestClientBootApplication.class)
@WebAppConfiguration
public class RestClientBootApplicationTest extends
    AbstractTestNGSpringContextTests {
  private static final String TEST_RECORD1 =
      "{\"identifier\": \"1\", \"origin\": \"Bratislava\", \"destination\": \"Dublin\"}";
  private static final String TEST_RECORD2 =
      "{\"identifier\": \"2\", \"origin\": \"Prague\", \"destination\": \"Paris\"}";

  private static final String DUBLIN = "Dublin";

  private static final String BRATISLAVA = "Bratislava";

  private static final String FLIGHTS_URL = "/flights";

  private MockRestServiceServer mockServer;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private FlightsClient flightsClient;

  @Value("${flight.server.hostname}")
  private String flightServerHostname;

  @BeforeMethod
  public void init() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void testPost() throws Exception {
    // GIVEN
    //@formatter:off
    mockServer.expect(requestTo(flightServerHostname + FLIGHTS_URL))
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
    // GIVEN
    //@formatter:off
    int testingIdentifier = 1;
    mockServer.expect(requestTo(flightServerHostname + FLIGHTS_URL + "/" + testingIdentifier))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(TEST_RECORD1, MediaType.APPLICATION_JSON));
    //@formatter:on

    // WHEN
    Flight flight = flightsClient.getFlight(testingIdentifier);

    // THEN
    mockServer.verify();
    assertEquals(flight.getIdentifier(), testingIdentifier);
    assertEquals(flight.getOrigin(), BRATISLAVA);
    assertEquals(flight.getDestination(), DUBLIN);
  }

  @Test
  public void testMultiGet() throws Exception {
    // GIVEN
    //@formatter:off
    mockServer.expect(requestTo(flightServerHostname + FLIGHTS_URL))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess("[ " + TEST_RECORD1 + ", " + TEST_RECORD2 + "]", 
          MediaType.APPLICATION_JSON));
    //@formatter:on

    // WHEN
    List<Flight> flights = flightsClient.getFlights();

    // THEN
    mockServer.verify();
    assertEquals(flights.get(0).getIdentifier(), 1);
    assertEquals(flights.get(0).getOrigin(), BRATISLAVA);
    assertEquals(flights.get(0).getDestination(), DUBLIN);
    assertEquals(flights.get(1).getIdentifier(), 2);
    assertEquals(flights.get(1).getOrigin(), "Prague");
    assertEquals(flights.get(1).getDestination(), "Paris");
  }

  @Test
  public void testDeleteFlight() throws Exception {
    // GIVEN
    //@formatter:off
    int testingIdentifier = 1;
    mockServer.expect(requestTo(flightServerHostname + FLIGHTS_URL + "/" + testingIdentifier))
      .andExpect(method(HttpMethod.DELETE))
      .andRespond(withSuccess());
    //@formatter:on

    // WHEN
    flightsClient.deleteFlight(testingIdentifier);

    // THEN
    mockServer.verify();
  }
}
