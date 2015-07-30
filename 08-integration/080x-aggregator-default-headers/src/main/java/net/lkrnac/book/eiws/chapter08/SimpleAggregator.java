package net.lkrnac.book.eiws.chapter08;

import java.util.List;

import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class SimpleAggregator {
  @Aggregator(inputChannel = "inChannel", outputChannel = "aggregatedChannel")
  public String aggregate(List<String> messages) {
    return messages.stream().reduce((m1, m2) -> m1 + "," + m2).get();
  }
}
