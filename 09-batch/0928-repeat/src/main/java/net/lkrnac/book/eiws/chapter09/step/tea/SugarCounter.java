package net.lkrnac.book.eiws.chapter09.step.tea;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@JobScope
@Component
public class SugarCounter {
  private int currentSugarCount;
  private int desiredSugarAmount;

  @Autowired
  public SugarCounter(@Value("#{jobParameters[sugarAmount]}") int desiredSugarAmount) {
    super();
    this.desiredSugarAmount = desiredSugarAmount;
    this.currentSugarCount = 0;
  }

  public boolean addSugar() {
    boolean result = currentSugarCount < desiredSugarAmount;
    currentSugarCount++;
    return result;
  }
}
