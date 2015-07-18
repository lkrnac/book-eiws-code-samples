package net.lkrnac.book.eiws.chapter07;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableJms
@EnableScheduling
@ImportResource(value = { "classpath:spring-jdbc-config.xml",
    "classpath:spring-jta-config.xml", "classpath:spring-jms-config.xml" })
public class JtaApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(JtaApplication.class, args);
  }
}
