package net.lkrnac.book.eiws.chapter03.ws.boot.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@ComponentScan
public class WebServiceConfiguration {
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("net.lkrnac.book.eiws.chapter03.ws.boot.model");
    return marshaller;
  }

  @Bean
  public WebServiceClient webServiceClient(Jaxb2Marshaller marshaller) {
    WebServiceClient client = new WebServiceClient();
    client.setDefaultUri("http://localhost:10303/0303-ws-bootservice");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}
