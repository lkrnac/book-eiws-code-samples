package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.test.simplemessage.CommonJmsSimpleMessageTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = ListenerAnnotationJmsAsyncApplication.class)
public class ListenerAnnotationJmsAsyncApplicationTests extends
    CommonJmsSimpleMessageTest {
}
