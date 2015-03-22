package net.lkrnac.book.eiws.chapter03.ws.interceptor.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@EnableWs
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ServerConfiguration extends WsConfigurerAdapter {
  public static final String NAMESPACE =
      "http://localhost:10305/0305-ws-interceptor-service";

  @Autowired
  private UserInterceptor userInterceptor;
  @Autowired
  private GlobalIntercetor globalInterceptor;

  @Bean
  public SimpleWsdl11Definition userDetails() {
    return new SimpleWsdl11Definition(new ClassPathResource(
        "userDetailsSchema.wsdl"));
  }

  @Bean
  public ServletRegistrationBean dispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/*");
  }

  @Override
  public void addInterceptors(List<EndpointInterceptor> interceptors) {
    interceptors.add(globalInterceptor);
    interceptors.add(userInterceptor);
    interceptors.add(new PayloadLoggingInterceptor());
    interceptors.add(new SoapEnvelopeLoggingInterceptor());

    PayloadValidatingInterceptor validationInterceptor =
        new PayloadValidatingInterceptor();
    SimpleXsdSchema schema =
        new SimpleXsdSchema(new ClassPathResource("userDetailsSchema.xsd"));
    validationInterceptor.setXsdSchema(schema);
    validationInterceptor.setValidateRequest(true);
    validationInterceptor.setValidateResponse(true);
    interceptors.add(validationInterceptor);
  }
}
