package net.lkrnac.book.eiws.chapter04.client;

import java.util.Arrays;
import java.util.List;

import net.lkrnac.book.eiws.chapter04.model.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FlightsClient {
  private RestTemplate restTemplate;
  private String flightsEndpointUrl;

  @Autowired
  public FlightsClient(
      @Value("${flight.server.hostname}") String flightServerHostname,
      RestTemplate restTemplate) {
    super();
    this.flightsEndpointUrl = flightServerHostname + "/flights";
    this.restTemplate = restTemplate;
  }

  public void bookFlight(Flight flight) {
    restTemplate.postForObject(flightsEndpointUrl, flight, Object.class);
  }

  public Flight getFlight(int identifier) {
    return restTemplate.getForObject(flightsEndpointUrl + "/" + identifier,
        Flight.class);
  }

  public List<Flight> getFlights() {
    Flight[] flightsArray =
        restTemplate.getForObject(flightsEndpointUrl, Flight[].class);
    return Arrays.asList(flightsArray);
  }

  public void deleteFlight(int identifier) {
    restTemplate.delete(flightsEndpointUrl + "/" + identifier);
  }
}
