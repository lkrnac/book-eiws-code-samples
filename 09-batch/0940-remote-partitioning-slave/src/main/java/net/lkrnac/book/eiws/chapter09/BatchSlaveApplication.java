package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableBatchProcessing
@ImportResource("classpath:batch-slave-config.xml")
public class BatchSlaveApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(BatchSlaveApplication.class);
  }
}
