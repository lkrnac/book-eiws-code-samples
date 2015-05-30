package net.lkrnac.book.eiws.chapter05.user;

import net.lkrnac.book.eiws.chapter05.user.JmsCustomConverterApplication;
import net.lkrnac.book.eiws.chapter05.user.test.CommonJmsUserMessageTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = JmsCustomConverterApplication.class)
public class JmsCustomConverterApplicationTests extends
    CommonJmsUserMessageTest {
}
