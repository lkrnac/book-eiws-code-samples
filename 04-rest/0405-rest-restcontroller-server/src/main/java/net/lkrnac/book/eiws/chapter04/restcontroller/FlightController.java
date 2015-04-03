package net.lkrnac.book.eiws.chapter04.restcontroller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {
  private final FlightService flightService;

  @Autowired
  public FlightController(FlightService flightService) {
    super();
    this.flightService = flightService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public Collection<Flight> getFlights() {
    return flightService.getAllFlights();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Flight getFlight(@PathVariable("id") int identifier) {
    return flightService.getFlight(identifier);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public void postFlight(@RequestBody Flight flight) {
    flightService.putFlight(flight);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteFlight(@PathVariable("id") int identifier) {
    flightService.deleteFlight(identifier);
  }
}
