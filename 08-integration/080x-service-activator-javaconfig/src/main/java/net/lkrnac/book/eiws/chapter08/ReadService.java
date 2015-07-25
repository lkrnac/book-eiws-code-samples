package net.lkrnac.book.eiws.chapter08;

import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.stereotype.Service;

@Service
public class ReadService {
  @InboundChannelAdapter(value = "inChannel", poller = @Poller(fixedRate = "1000"))
  public String read() {
    return "simple message";
  }
}
