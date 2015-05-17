package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.test.simplemessage.CommonJmsSimpleMessageTest;
import net.lkrnac.book.eiws.chapter05.test.simplemessage.CommonJmsSimpleMessageTest.TestSimpleMessageConfiguration;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = { JmsMessageAbstrationsAsyncApplication.class,
    TestSimpleMessageConfiguration.class })
public class JmsMessageAbstrationsApplicationTests extends CommonJmsSimpleMessageTest {
}
