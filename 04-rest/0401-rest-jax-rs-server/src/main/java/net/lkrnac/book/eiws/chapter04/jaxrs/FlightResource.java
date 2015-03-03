package net.lkrnac.book.eiws.chapter04.jaxrs;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/flight")
@Component
public class FlightResource {
  private final FlightService flightService;

  @Autowired
  public FlightResource(FlightService flightService) {
    super();
    this.flightService = flightService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Collection<Flight> getFlights() {
    return flightService.getAllFlights();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Flight getFlight(@PathParam("id") int identifier) {
    return flightService.getFlight(identifier);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postFlight(Flight flight) {
    flightService.putFlight(flight);
    return Response.status(201).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteFlight(@PathParam("id") int identifier) {
    flightService.deleteFlight(identifier);
    return Response.ok().build();
  }
}
