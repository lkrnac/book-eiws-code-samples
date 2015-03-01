package net.lkrnac.book.eiws.chapter04.jaxrs;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.lkrnac.book.eiws.BiFunctionRetryHandler;
import net.lkrnac.book.eiws.ProcessExecutor;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = RestJaxrsApplication.class)
@WebAppConfiguration
public class RestJaxrsApplicationITCase extends
    AbstractTestNGSpringContextTests {
  private static final String FLIGHT_URL = "http://localhost:10401/flight";

  private static final int RETRY_TIMEOUT = 10000;

  private final RestTemplate restTemplate = new RestTemplate();
  private Process process;

  @BeforeClass
  public void startApp() throws IOException, URISyntaxException {
    process =
        new ProcessExecutor().execute("", "0401-rest-jax-rs-server-exec.jar");

    BiFunctionRetryHandler<URI, Class<Flight[]>, Flight[]> retryHandler =
        new BiFunctionRetryHandler<>();

    retryHandler.retry(restTemplate::getForObject, new URI(FLIGHT_URL),
        Flight[].class, RETRY_TIMEOUT);
  }

  @AfterClass
  public void stopApp() throws InterruptedException {
    process.destroyForcibly();
    process.waitFor();
  }

  @Test
  public void testPost() {
    // GIVEN
    Flight flight = createTestingRecord(1);

    // WHEN
    ResponseEntity<String> response =
        restTemplate.postForEntity(FLIGHT_URL, flight, String.class);

    // THEN
    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
  }

  @Test
  public void testSingleGet() {
    // GIVEN
    Flight expectedFlight = createTestingRecord(1);
    restTemplate.postForEntity(FLIGHT_URL, expectedFlight, String.class);

    // WHEN
    Flight actualFlight =
        restTemplate.getForObject(
            FLIGHT_URL + "/" + expectedFlight.getIdentifier(), Flight.class);

    // THEN
    assertEquals(actualFlight.getDestination(), expectedFlight.getDestination());
    assertEquals(actualFlight.getOrigin(), expectedFlight.getOrigin());
  }

  @Test
  public void testMultiGet() {
    // GIVEN
    Flight expectedFlight1 = createTestingRecord(1);
    restTemplate.postForEntity(FLIGHT_URL, expectedFlight1, String.class);
    Flight expectedFlight2 = createTestingRecord(2);
    restTemplate.postForEntity(FLIGHT_URL, expectedFlight2, String.class);

    // WHEN
    Flight[] actualFlights =
        restTemplate.getForObject(FLIGHT_URL, Flight[].class);

    // THEN
    assertEquals(actualFlights[0].getDestination(),
        expectedFlight1.getDestination());
    assertEquals(actualFlights[0].getOrigin(), expectedFlight1.getOrigin());
    assertEquals(actualFlights[1].getDestination(),
        expectedFlight2.getDestination());
    assertEquals(actualFlights[1].getOrigin(), expectedFlight2.getOrigin());
  }

  private Flight createTestingRecord(int idx) {
    Flight flight = new Flight();
    flight.setIdentifier(idx);
    flight.setOrigin("Bratislava" + idx);
    flight.setDestination("Dublin" + idx);
    return flight;
  }
}
