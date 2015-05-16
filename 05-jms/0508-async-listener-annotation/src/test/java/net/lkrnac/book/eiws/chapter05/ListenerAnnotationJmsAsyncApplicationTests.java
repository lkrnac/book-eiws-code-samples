package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.test.CommonJmsTest;
import net.lkrnac.book.eiws.chapter05.test.CommonJmsTest.TestConfiguration;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = { ListenerAnnotationJmsAsyncApplication.class,
    TestConfiguration.class })
public class ListenerAnnotationJmsAsyncApplicationTests extends CommonJmsTest {
}
