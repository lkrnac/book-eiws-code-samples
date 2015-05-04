package net.lkrnac.book.eiws.chapter04.client;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Repository;

@Repository
public class UserActionsRepository {
  public Collection<String> getUserActions(int identifier) {
    return Arrays.asList(new String[] { "dummy action 1", "dummy action 2", });
  }
}
