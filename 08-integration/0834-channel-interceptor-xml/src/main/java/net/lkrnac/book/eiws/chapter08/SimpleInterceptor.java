package net.lkrnac.book.eiws.chapter08;

import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleInterceptor implements ChannelInterceptor {
  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    log.info("Going to send message {} to channel {}", message.getPayload(),
        channel);
    return message;
  }

  @Override
  public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    log.info("Sending of message {} to channel {} finished",
        message.getPayload(), channel);
  }

  @Override
  public void afterSendCompletion(Message<?> message, MessageChannel channel,
      boolean sent, Exception ex) {
    log.info("Message {} was successfully send to channel {}",
        message.getPayload(), channel);
  }

  @Override
  public boolean preReceive(MessageChannel channel) {
    log.info("Going to receive message from channel {}", channel);
    return true;
  }

  @Override
  public Message<?> postReceive(Message<?> message, MessageChannel channel) {
    log.info("Receiving of message {} from channel {} finished",
        message.getPayload(), channel);
    return message;
  }

  @Override
  public void afterReceiveCompletion(Message<?> message,
      MessageChannel channel, Exception ex) {
    log.info("Message {} was successfully received from channel {}",
        message.getPayload(), channel);
  }
}
