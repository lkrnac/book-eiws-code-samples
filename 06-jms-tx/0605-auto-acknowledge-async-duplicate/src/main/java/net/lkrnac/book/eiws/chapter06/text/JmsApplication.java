package net.lkrnac.book.eiws.chapter06.text;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @Configuration
// @ComponentScan
// @EnableJms
public class JmsApplication {
  public static void main(String[] args) throws Exception {
    // BrokerService broker = new BrokerService();
    // broker.addConnector("vm://localhost");
    // broker.start();

    SpringApplication.run(JmsApplication.class, args);
  }
}
