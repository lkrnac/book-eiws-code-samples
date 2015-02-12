package net.lkrnac.book.eiws.chapter03.ws.error.server;

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
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ServerConfiguration extends WsConfigurationSupport {
  public static final String NAMESPACE =
      "http://localhost:10303/0303-ws-error-service";

  @Bean
  public XsdSchema userDetailsSchema() {
    return new SimpleXsdSchema(new ClassPathResource("userDetails.xsd"));
  }

  @Bean
  public DefaultWsdl11Definition userDetails(XsdSchema userDetailsSchema) {
    DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
    wsdlDefinition.setTargetNamespace(NAMESPACE);
    wsdlDefinition.setSchema(userDetailsSchema);
    wsdlDefinition.setPortTypeName("UserDetailsPort");
    wsdlDefinition.setLocationUri("/wsdl/");
    return wsdlDefinition;
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
