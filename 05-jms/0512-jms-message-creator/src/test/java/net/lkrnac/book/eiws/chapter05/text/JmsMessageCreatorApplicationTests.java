package net.lkrnac.book.eiws.chapter05.text;

import net.lkrnac.book.eiws.chapter05.text.JmsMessageCreatorApplication;
import net.lkrnac.book.eiws.chapter05.text.test.CommonJmsSimpleMessageTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = JmsMessageCreatorApplication.class)
public class JmsMessageCreatorApplicationTests extends
    CommonJmsSimpleMessageTest {
}
