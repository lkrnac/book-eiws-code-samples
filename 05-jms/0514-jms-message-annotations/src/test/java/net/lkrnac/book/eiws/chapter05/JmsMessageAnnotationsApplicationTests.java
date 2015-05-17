package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.test.user.CommonJmsUserWithRoleTest;
import net.lkrnac.book.eiws.chapter05.test.user.CommonJmsUserWithRoleTest.TestUserWithRoleConfiguration;

import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = {
    JmsMessageAnnotationsApplication.class, TestUserWithRoleConfiguration.class })
public class JmsMessageAnnotationsApplicationTests extends
    CommonJmsUserWithRoleTest {
}
