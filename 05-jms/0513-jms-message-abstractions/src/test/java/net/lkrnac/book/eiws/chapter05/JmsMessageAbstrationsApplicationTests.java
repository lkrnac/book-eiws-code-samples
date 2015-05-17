package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.test.simplemessage.CommonJmsSimpleMessageTest.TestSimpleMessageConfiguration;
import net.lkrnac.book.eiws.chapter05.test.user.CommonJmsUserMessageTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = {
    JmsMessageAbstrationsAsyncApplication.class,
    TestSimpleMessageConfiguration.class })
public class JmsMessageAbstrationsApplicationTests extends
    CommonJmsUserMessageTest {
}
