package net.lkrnac.book.eiws.chapter04.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FlightsClient {
  private RestTemplate restTemplate;

  @Autowired
  public FlightsClient(RestTemplate restTemplate) {
    super();
    this.restTemplate = restTemplate;
  }

  public void bookFlight(Flight flight) {
    restTemplate.postForObject("/flights", flight, Object.class);
  }

}
