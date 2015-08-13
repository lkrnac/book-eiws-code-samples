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
    log.info("Going to send message " + message + " to channel " + channel);
    return message;
  }

  @Override
  public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    log.info("Message " + message + " was successfully send to channel "
        + channel);
  }

  @Override
  public void afterSendCompletion(Message<?> message, MessageChannel channel,
      boolean sent, Exception ex) {
    log.info("Message " + message + " was send to channel " + channel);
  }

  @Override
  public boolean preReceive(MessageChannel channel) {
    log.info("Going to receive message to channel " + channel);
    return true;
  }

  @Override
  public Message<?> postReceive(Message<?> message, MessageChannel channel) {
    log.info("Message " + message + " was successfully received to channel "
        + channel);
    return message;
  }

  @Override
  public void afterReceiveCompletion(Message<?> message,
      MessageChannel channel, Exception ex) {
    log.info("Message " + message + " was received to channel " + channel);
  }
}
