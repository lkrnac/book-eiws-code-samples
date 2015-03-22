package net.lkrnac.book.eiws.chapter03.ws.javaconfig.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

@EnableWs
@ComponentScan
@Configuration
public class ServerConfiguration {
  public static final String NAMESPACE =
      "http://localhost:10302/0302-ws-javaconfig-service";

  @Bean
  public SimpleWsdl11Definition userDetails() {
    return new SimpleWsdl11Definition(new ClassPathResource(
        "userDetailsSchema.wsdl"));
  }
}
