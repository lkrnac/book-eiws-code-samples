package net.lkrnac.book.eiws.chapter06.text;

import net.lkrnac.book.eiws.chapter06.text.ClientAckApplication;
import net.lkrnac.book.eiws.chapter06.text.test.CommonJmsSimpleMessageTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = ClientAckApplication.class)
public class ClientAckApplicationTests extends
    CommonJmsSimpleMessageTest {
}
