package net.lkrnac.book.eiws.chapter08;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;

@MessageEndpoint
public class SimpleSplitter {
  @Splitter(inputChannel = "inChannel", outputChannel = "splitChannel")
  public List<String> splitMessage(String message) {
    return Arrays.asList(StringUtils.split(message, ";"));
  }
}
