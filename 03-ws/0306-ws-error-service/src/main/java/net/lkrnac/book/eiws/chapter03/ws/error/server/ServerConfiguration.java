package net.lkrnac.book.eiws.chapter03.ws.error.server;

import java.util.Properties;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurationSupport;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

@EnableWs
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ServerConfiguration extends WsConfigurationSupport {
  public static final String NAMESPACE =
      "http://localhost:10306/0306-ws-error-service";

  @Bean
  public SimpleWsdl11Definition userDetailsSchema() {
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

  @Bean
  public SoapFaultMappingExceptionResolver exceptionResolver() {
    SoapFaultMappingExceptionResolver exceptionResolver =
        new SoapFaultMappingExceptionResolver();

    SoapFaultDefinition defaultSoapFault = new SoapFaultDefinition();
    defaultSoapFault.setFaultCode(SoapFaultDefinition.SERVER);
    exceptionResolver.setDefaultFault(defaultSoapFault);

    Properties errorMappings = new Properties();
    errorMappings.put(IllegalStateException.class.getName(), "CLIENT");
    // SoapFaultDefinition.CLIENT);
    exceptionResolver.setExceptionMappings(errorMappings);
    exceptionResolver.setOrder(1);

    return exceptionResolver;
  }
}
