package net.lkrnac.book.eiws.chapter03.ws.objectfactory.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
@ComponentScan
public class WsObjectFactoryClientConfiguration {
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("net.lkrnac.book.eiws.chapter03.ws.boot.model");
    return marshaller;
  }

  @Bean
  public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
    webServiceTemplate.setMarshaller(marshaller);
    webServiceTemplate.setUnmarshaller(marshaller);
    webServiceTemplate
        .setDefaultUri("http://localhost:10308/0308-ws-objectfactory-service");

    HttpComponentsMessageSender messageSender =
        new HttpComponentsMessageSender();
    messageSender.setConnectionTimeout(100);
    messageSender.setMaxTotalConnections(10);
    webServiceTemplate.setMessageSender(messageSender);
    return webServiceTemplate;
  }
}
