package net.lkrnac.book.eiws.chapter05.user.test;

import net.lkrnac.book.eiws.chapter05.user.User;
import net.lkrnac.book.eiws.chapter05.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonJmsUserMessageTest extends AbstractTestNGSpringContextTests {
  @Autowired
  private UserService userService;

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Test(timeOut = 3000)
  public void testJms() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN
    TestingUserService testinguserService = (TestingUserService) userService;

    // THEN
    User user = testinguserService.getUser();
    Assert.assertEquals(user.getEmail(), "lubos.krnac@gmail.com");
    Assert.assertEquals(user.getName(), "Lubos Krnac");
    User user1 = testinguserService.getUser();
    Assert.assertEquals(user1.getEmail(), "lubos.krnac@gmail.com");
    Assert.assertEquals(user1.getName(), "Lubos Krnac");
  }
}
