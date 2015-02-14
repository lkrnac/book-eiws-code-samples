package net.lkrnac.book.eiws.chapter03.ws.soapaction.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurationSupport;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

@EnableWs
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ServerConfiguration extends WsConfigurationSupport {
  public static final String NAMESPACE =
      "http://localhost:10307/0307-ws-soapaction-service";

  @Bean
  public SimpleWsdl11Definition userDetails() {
    return new SimpleWsdl11Definition(new ClassPathResource("userDetails.xsd"));
  }

  @Bean
  public ServletRegistrationBean dispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/*");
  }
}
