package net.lkrnac.book.eiws.chapter08.in;

import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.stereotype.Service;

@Service
public class ReadServiceAnnotated {
  //@formatter:off
  @InboundChannelAdapter(value = "inChannel", 
      poller = @Poller(fixedRate = "1000", maxMessagesPerPoll = "1"))
  //@formatter:on
  public String read() {
    return "simple message";
  }
}
