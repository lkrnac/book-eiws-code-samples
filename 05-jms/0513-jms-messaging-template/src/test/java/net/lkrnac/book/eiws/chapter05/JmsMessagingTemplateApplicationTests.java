package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.test.user.CommonJmsUserWithRoleTest;
import net.lkrnac.book.eiws.chapter05.test.user.CommonJmsUserWithRoleTest.TestUserWithRoleConfiguration;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = {
    JmsMessagingTemplateApplication.class, TestUserWithRoleConfiguration.class })
public class JmsMessagingTemplateApplicationTests extends
    CommonJmsUserWithRoleTest {
}
