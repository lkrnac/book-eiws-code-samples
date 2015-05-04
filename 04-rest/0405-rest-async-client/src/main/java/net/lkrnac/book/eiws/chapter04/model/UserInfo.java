package net.lkrnac.book.eiws.chapter04.model;

import java.util.Collection;

import lombok.Value;

@Value
public class UserInfo {
  private User user;
  private Collection<String> userActions;
}
