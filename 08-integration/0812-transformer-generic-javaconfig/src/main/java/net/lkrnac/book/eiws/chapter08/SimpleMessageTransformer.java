package net.lkrnac.book.eiws.chapter08;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

@MessageEndpoint
public class SimpleMessageTransformer {
  @Transformer(inputChannel = "inChannel", outputChannel = "transformedChannel")
  public String transformMessage(String message) {
    return new String(message + " transformed");
  }
}
