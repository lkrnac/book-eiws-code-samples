package net.lkrnac.book.eiws.chapter04.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    super();
    register(FlightResource.class);
  }
}