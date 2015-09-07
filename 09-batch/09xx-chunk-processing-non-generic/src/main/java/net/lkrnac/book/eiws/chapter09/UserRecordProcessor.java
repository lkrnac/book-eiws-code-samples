package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserRecordProcessor implements ItemProcessor<User, User> {
  @Override
  public User process(User user) throws Exception {
    String firstEmailPart = StringUtils.split(user.getEmail(), "@")[0];
    String newEmail = firstEmailPart + "@greedycompany.com";

    User userWithGmail = new User();
    userWithGmail.setEmail(newEmail);
    userWithGmail.setName(user.getName());
    return userWithGmail;
  }
}
