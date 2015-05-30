package net.lkrnac.book.eiws.chapter05.test.user;

import net.lkrnac.book.eiws.chapter05.User;
import net.lkrnac.book.eiws.chapter05.UserWithRoleService;
import net.lkrnac.book.eiws.chapter05.test.user.TestingUserWithRoleService.UserWithRoleTuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonJmsUserWithRoleTest extends AbstractTestNGSpringContextTests {
  @Autowired
  private UserWithRoleService userHandler;

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Configuration
  public static class TestUserWithRoleConfiguration {
    @Bean
    @Primary
    @Profile("integration-test")
    public UserWithRoleService userHandler() {
      return new TestingUserWithRoleService();
    }
  }

  @Test(timeOut = 3000)
  public void contextLoads() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN
    TestingUserWithRoleService testingUserHandler =
        (TestingUserWithRoleService) userHandler;

    // THEN
    UserWithRoleTuple userWithRoleTuple =
        testingUserHandler.getUserWithRoleTuple();
    verifyTuple(userWithRoleTuple);

    UserWithRoleTuple userWithRoleTuple1 =
        testingUserHandler.getUserWithRoleTuple();
    verifyTuple(userWithRoleTuple1);
  }

  private void verifyTuple(UserWithRoleTuple userWithRoleTuple) {
    User user = userWithRoleTuple.getUser();
    Assert.assertEquals(user.getEmail(), "lubos.krnac@gmail.com");
    Assert.assertEquals(user.getName(), "Lubos Krnac");
    Assert.assertEquals(userWithRoleTuple.getRole(), "admin");
  }
}
