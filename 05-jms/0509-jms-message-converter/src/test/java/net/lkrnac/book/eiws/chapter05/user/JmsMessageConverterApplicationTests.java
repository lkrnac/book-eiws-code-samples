package net.lkrnac.book.eiws.chapter05.user;

import net.lkrnac.book.eiws.chapter05.user.JmsMessageConverterApplication;
import net.lkrnac.book.eiws.chapter05.user.test.CommonJmsUserMessageTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = JmsMessageConverterApplication.class)
public class JmsMessageConverterApplicationTests extends
    CommonJmsUserMessageTest {
}
