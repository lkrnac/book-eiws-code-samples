package net.lkrnac.book.eiws.chapter05.text;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.lkrnac.book.eiws.chapter05.text.PubSubHandler;
import lombok.Value;

public class TestingPubSubHandler implements PubSubHandler {
  private final BlockingQueue<PubSubTuple> queue = new ArrayBlockingQueue<>(10);

  @Value
  class PubSubTuple {
    private int listenerId;
    private String message;
  }

  @Override
  public void handleMessage(int listenerId, String message) {
    queue.add(new PubSubTuple(listenerId, message));
  }

  public PubSubTuple getPubSubTuple() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
