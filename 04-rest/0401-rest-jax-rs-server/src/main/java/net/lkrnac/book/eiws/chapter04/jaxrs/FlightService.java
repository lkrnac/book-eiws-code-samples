package net.lkrnac.book.eiws.chapter04.jaxrs;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class FlightService {
  private Map<Integer, Flight> flights = Collections
      .synchronizedMap(new HashMap<>());

  public Collection<Flight> getAllFlights() {
    return Collections.unmodifiableCollection(flights.values());
  }

  public Flight getFlight(int id) {
    return flights.get(id);
  }

  public void putFlight(Flight flight) {
    flights.put(flight.getId(), flight);
  }
}
