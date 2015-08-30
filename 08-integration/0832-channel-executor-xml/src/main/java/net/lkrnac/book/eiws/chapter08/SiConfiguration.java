package net.lkrnac.book.eiws.chapter08;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiConfiguration {
  @Bean
  public Executor executor() {
    return Executors.newFixedThreadPool(10);
  }
}
