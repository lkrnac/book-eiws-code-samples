package net.lkrnac.book.eiws.chapter09.step.tea;

import lombok.Data;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

@Data
@JobScope
@Component
public class SugarCounter {
  private int spoonsCount = 0;
}
