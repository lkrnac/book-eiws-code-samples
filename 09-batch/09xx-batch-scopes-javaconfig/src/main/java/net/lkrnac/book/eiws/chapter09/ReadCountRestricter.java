package net.lkrnac.book.eiws.chapter09;

import lombok.Getter;
import lombok.Setter;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@StepScope
@Component
public class ReadCountRestricter {
  private long countToProcess;

  @Setter
  private long readCount;

  @Autowired
  public ReadCountRestricter(
      @Value("#{jobParameters[recordCountToProcess]}") long countToProcess) {
    super();
    this.countToProcess = countToProcess;
  }
}
