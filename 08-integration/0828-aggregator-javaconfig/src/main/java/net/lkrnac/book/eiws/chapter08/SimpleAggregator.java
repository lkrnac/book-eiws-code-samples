package net.lkrnac.book.eiws.chapter08;

import java.util.List;

import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ReleaseStrategy;

@MessageEndpoint
public class SimpleAggregator {
  @Aggregator(inputChannel = "inChannel", outputChannel = "aggregatedChannel")
  public String aggregate(List<String> messages) {
    return messages.stream().reduce((m1, m2) -> m1 + "," + m2).get();
  }

  @ReleaseStrategy
  public boolean releaseChecker(List<String> messages) {
    return messages.size() == 2;
  }

  @CorrelationStrategy
  public String correlateBy(String message) {
    return message.substring(7);
  }
}
