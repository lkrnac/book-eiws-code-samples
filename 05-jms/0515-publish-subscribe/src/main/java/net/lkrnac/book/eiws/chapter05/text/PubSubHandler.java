package net.lkrnac.book.eiws.chapter05.text;

public interface PubSubHandler {
  void handleMessage(int listenerId, String message);
}
