package net.lkrnac.book.eiws.chapter05.user;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import net.lkrnac.book.eiws.chapter05.user.User;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class UserMessageConverter implements MessageConverter {
  private static final String DELIMITER = ";";

  @Override
  public Message toMessage(Object object, Session session) throws JMSException,
      MessageConversionException {
    User user = (User) object;
    String userString = user.getEmail() + DELIMITER + user.getName();
    return session.createTextMessage(userString);
  }

  @Override
  public Object fromMessage(Message message) throws JMSException,
      MessageConversionException {
    String userMessage = message.getBody(String.class);
    String[] userStringChunks = userMessage.split(DELIMITER);
    User user = new User();
    user.setEmail(userStringChunks[0]);
    user.setName(userStringChunks[1]);
    return user;
  }
}
