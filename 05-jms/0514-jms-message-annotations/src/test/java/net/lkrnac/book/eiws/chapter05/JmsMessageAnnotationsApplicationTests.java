package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.test.user.CommonJmsUserMessageTest;
import net.lkrnac.book.eiws.chapter05.test.user.CommonJmsUserMessageTest.TestUserConfiguration;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = {
    JmsMessageAnnotationsApplication.class, TestUserConfiguration.class })
public class JmsMessageAnnotationsApplicationTests extends
    CommonJmsUserMessageTest {
}
