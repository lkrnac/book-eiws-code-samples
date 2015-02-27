package net.lkrnac.book.eiws.chapter04.jaxrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  // @Bean
  // public ServletRegistrationBean jerseyRegistration(
  // @Qualifier("jerseyServletRegistration") ServletRegistrationBean
  // jerseyServletRegistration) {
  // jerseyServletRegistration.addInitParameter(
  // "com.sun.jersey.api.json.POJOMappingFeature", "true");
  // return jerseyServletRegistration;
  // }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  // @Override
  // protected SpringApplicationBuilder configure(
  // SpringApplicationBuilder application) {
  // return application.sources(Application.class);
  // }
  //
  // public static void main(String[] args) {
  // new Application()
  // .configure(new SpringApplicationBuilder(Application.class)).run(args);
  // }
}
