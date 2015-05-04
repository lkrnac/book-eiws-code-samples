package net.lkrnac.book.eiws.chapter04.client;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.client.AsyncRestTemplate;

@Configuration
@ComponentScan
public class ClientConfiguration {
  @Bean
  public AsyncRestTemplate asyncRestTemplate() {
    Executor executor = Executors.newFixedThreadPool(10);
    ConcurrentTaskExecutor taskExecutor = new ConcurrentTaskExecutor(executor);
    return new AsyncRestTemplate(taskExecutor);
  }
}
