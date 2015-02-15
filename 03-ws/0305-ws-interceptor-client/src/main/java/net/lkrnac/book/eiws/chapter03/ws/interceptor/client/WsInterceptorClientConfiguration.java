package net.lkrnac.book.eiws.chapter03.ws.interceptor.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@Configuration
@ComponentScan
public class WsInterceptorClientConfiguration {
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller
        .setContextPath("net.lkrnac.book.eiws.chapter03.ws.interceptor.model");
    return marshaller;
  }

  @Bean
  public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller,
      UserInterceptor userInterceptor) {
    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
    webServiceTemplate.setMarshaller(marshaller);
    webServiceTemplate.setUnmarshaller(marshaller);
    webServiceTemplate
        .setDefaultUri("http://localhost:10305/0305-ws-interceptor-service");

    ClientInterceptor[] interceptors =
        new ClientInterceptor[] { userInterceptor };
    webServiceTemplate.setInterceptors(interceptors);
    return webServiceTemplate;
  }
}
