package net.lkrnac.book.eiws.chapter04.boot;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class FlightService {
  private final ConcurrentHashMap<Integer, Flight> flights =
      new ConcurrentHashMap<>();

  public Collection<Flight> getAllFlights() {
    return Collections.unmodifiableCollection(flights.values());
  }

  public Flight getFlight(int identifier) {
    return flights.get(identifier);
  }

  public void putFlight(Flight flight) {
    flights.put(flight.getIdentifier(), flight);
  }

  public Flight deleteFlight(int identifier) {
    return flights.remove(identifier);
  }
}
