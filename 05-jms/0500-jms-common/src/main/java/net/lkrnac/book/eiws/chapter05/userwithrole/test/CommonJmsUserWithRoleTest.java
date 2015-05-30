package net.lkrnac.book.eiws.chapter05.userwithrole.test;

import net.lkrnac.book.eiws.chapter05.user.User;
import net.lkrnac.book.eiws.chapter05.userwithrole.UserWithRoleService;
import net.lkrnac.book.eiws.chapter05.userwithrole.test.TestingUserWithRoleService.UserWithRoleTuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonJmsUserWithRoleTest extends AbstractTestNGSpringContextTests {
  @Autowired
  private UserWithRoleService userService;

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Test(timeOut = 3000)
  public void contextLoads() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN
    TestingUserWithRoleService testinguserService =
        (TestingUserWithRoleService) userService;

    // THEN
    UserWithRoleTuple userWithRoleTuple =
        testinguserService.getUserWithRoleTuple();
    verifyTuple(userWithRoleTuple);

    UserWithRoleTuple userWithRoleTuple1 =
        testinguserService.getUserWithRoleTuple();
    verifyTuple(userWithRoleTuple1);
  }

  private void verifyTuple(UserWithRoleTuple userWithRoleTuple) {
    User user = userWithRoleTuple.getUser();
    Assert.assertEquals(user.getEmail(), "lubos.krnac@gmail.com");
    Assert.assertEquals(user.getName(), "Lubos Krnac");
    Assert.assertEquals(userWithRoleTuple.getRole(), "admin");
  }
}
