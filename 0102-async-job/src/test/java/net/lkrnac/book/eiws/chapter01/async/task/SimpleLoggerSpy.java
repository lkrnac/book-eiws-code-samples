package net.lkrnac.book.eiws.chapter01.async.task;

import static org.mockito.Mockito.spy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SimpleLoggerSpy {
  @Bean
  @Primary
  public SimpleLogger registersimpleLoggerSpy() {
    return spy(new SimpleLogger());
  }
}
