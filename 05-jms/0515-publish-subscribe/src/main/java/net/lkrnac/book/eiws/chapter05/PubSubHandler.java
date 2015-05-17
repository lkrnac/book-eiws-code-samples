package net.lkrnac.book.eiws.chapter05;

public interface PubSubHandler {
  void handleMessage(int listenerId, String message);
}
