package net.lkrnac.book.eiws.chapter03.ws.boot.client;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class WsBootClientApplication {

  public static void main(String[] args) {
    SpringApplication.run("classpath:ws-client-config.xml", args);
  }

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("");
    return marshaller;
  }

  @Bean
  public WebServiceClient webServiceClient(Jaxb2Marshaller marshaller) {
    WebServiceClient client = new WebServiceClient();
    client.setDefaultUri("http://wsf.cdyne.com/WeatherWS/Weather.asmx");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return null;
  }

}
