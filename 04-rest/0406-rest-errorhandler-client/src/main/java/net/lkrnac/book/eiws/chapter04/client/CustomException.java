package net.lkrnac.book.eiws.chapter04.client;

public class CustomException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public CustomException(String message) {
    super(message);
  }
}
