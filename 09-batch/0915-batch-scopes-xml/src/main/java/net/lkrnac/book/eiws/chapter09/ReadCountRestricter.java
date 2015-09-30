package net.lkrnac.book.eiws.chapter09;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ReadCountRestricter {
  private long countToProcess;

  @Setter
  private long readCount;

  public ReadCountRestricter(long countToProcess) {
    super();
    this.countToProcess = countToProcess;
  }
}
