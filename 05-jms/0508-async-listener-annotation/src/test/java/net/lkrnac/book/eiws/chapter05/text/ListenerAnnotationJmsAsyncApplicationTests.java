package net.lkrnac.book.eiws.chapter05.text;

import net.lkrnac.book.eiws.chapter05.text.ListenerAnnotationJmsAsyncApplication;
import net.lkrnac.book.eiws.chapter05.text.test.CommonJmsSimpleMessageTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = ListenerAnnotationJmsAsyncApplication.class)
public class ListenerAnnotationJmsAsyncApplicationTests extends
    CommonJmsSimpleMessageTest {
}
