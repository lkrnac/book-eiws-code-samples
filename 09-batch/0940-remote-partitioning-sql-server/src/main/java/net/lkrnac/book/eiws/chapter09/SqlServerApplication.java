package net.lkrnac.book.eiws.chapter09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ImportResource("classpath:sql-server-config.xml")
public class SqlServerApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(SqlServerApplication.class);
  }
}
