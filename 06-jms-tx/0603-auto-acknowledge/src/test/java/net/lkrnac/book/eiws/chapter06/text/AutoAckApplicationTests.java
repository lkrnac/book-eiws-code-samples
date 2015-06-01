package net.lkrnac.book.eiws.chapter06.text;

import net.lkrnac.book.eiws.chapter06.text.AutoAckApplication;
import net.lkrnac.book.eiws.chapter06.text.test.CommonJmsSimpleMessageTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = AutoAckApplication.class)
public class AutoAckApplicationTests extends
    CommonJmsSimpleMessageTest {
}
