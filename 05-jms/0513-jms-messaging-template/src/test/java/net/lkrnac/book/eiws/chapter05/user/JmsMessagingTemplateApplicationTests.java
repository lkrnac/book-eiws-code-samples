package net.lkrnac.book.eiws.chapter05.user;

import net.lkrnac.book.eiws.chapter05.user.JmsMessagingTemplateApplication;
import net.lkrnac.book.eiws.chapter05.userwithrole.test.CommonJmsUserWithRoleTest;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = JmsMessagingTemplateApplication.class)
public class JmsMessagingTemplateApplicationTests extends
    CommonJmsUserWithRoleTest {
}
