package net.lkrnac.book.eiws.chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.MessageChannel;

@MessageEndpoint
public class SimpleRourter {
  private MessageChannel route1Channel;
  private MessageChannel route2Channel;

  @Autowired
  public SimpleRourter(MessageChannel route1Channel,
      MessageChannel route2Channel) {
    super();
    this.route1Channel = route1Channel;
    this.route2Channel = route2Channel;
  }

  @Router(inputChannel = "inChannel")
  public MessageChannel route(String message) {
    return (message.contains("1") ? route1Channel : route2Channel);
  }
}
