package net.lkrnac.book.eiws.chapter05.pubsub;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import lombok.Value;
import net.lkrnac.book.eiws.chapter05.pubsub.PubSubService;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("integration-test")
@Primary
@Service
public class TestingPubSubService extends PubSubService {
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
