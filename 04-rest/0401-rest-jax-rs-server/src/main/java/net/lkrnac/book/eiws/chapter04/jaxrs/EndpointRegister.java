package net.lkrnac.book.eiws.chapter04.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class EndpointRegister extends ResourceConfig {
  public EndpointRegister() {
    super();
    register(FlightResource.class);
  }
}