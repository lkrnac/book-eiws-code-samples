package net.lkrnac.book.eiws.chapter03.ws.javaconfig.server;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class ServerConfiguration extends WsConfigurerAdapter {
  public static final String NAMESPACE =
      "http://lkrnac.net/book/eiws/chapter03/ws/javaconfig/model";

  @Bean
  public ServletRegistrationBean dispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/*");
  }

  @Bean
  public DefaultWsdl11Definition userDetails(XsdSchema userDetailsSchema) {
    DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
    wsdlDefinition.setTargetNamespace(NAMESPACE);
    wsdlDefinition.setSchema(userDetailsSchema);
    wsdlDefinition.setPortTypeName("UserDetailsPort");
    wsdlDefinition.setLocationUri("/");
    return wsdlDefinition;
  }

  @Bean
  public XsdSchema userDetailsSchema() {
    return new SimpleXsdSchema(new ClassPathResource("user-details.xsd"));
  }
}
