package net.lkrnac.book.eiws.chapter05.text;

import net.lkrnac.book.eiws.chapter05.text.JmsCachingConnectionFactoryApplication;
import net.lkrnac.book.eiws.chapter05.text.test.CommonJmsSimpleMessageTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = JmsCachingConnectionFactoryApplication.class)
public class JmsCachingConnectionFactoryApplicationTests extends
    CommonJmsSimpleMessageTest {
}
