package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
@ImportResource("classpath:batch-config.xml")
public class BatchApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(BatchApplication.class);
  }
}
