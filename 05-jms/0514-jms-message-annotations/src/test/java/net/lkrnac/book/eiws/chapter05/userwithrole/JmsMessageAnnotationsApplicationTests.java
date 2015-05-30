package net.lkrnac.book.eiws.chapter05.userwithrole;

import net.lkrnac.book.eiws.chapter05.userwithrole.JmsMessageAnnotationsApplication;
import net.lkrnac.book.eiws.chapter05.userwithrole.test.CommonJmsUserWithRoleTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = JmsMessageAnnotationsApplication.class)
public class JmsMessageAnnotationsApplicationTests extends
    CommonJmsUserWithRoleTest {
}
