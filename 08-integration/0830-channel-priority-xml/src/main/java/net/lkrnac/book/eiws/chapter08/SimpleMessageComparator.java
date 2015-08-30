package net.lkrnac.book.eiws.chapter08;

import java.util.Comparator;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageComparator implements Comparator<Message<String>> {
  @Override
  public int compare(Message<String> o1, Message<String> o2) {
    return -1 * o1.getPayload().compareTo(o2.getPayload());
  }
}
