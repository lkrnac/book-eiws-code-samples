package net.lkrnac.book.eiws.chapter05.test.user;

import net.lkrnac.book.eiws.chapter05.User;
import net.lkrnac.book.eiws.chapter05.UserHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonJmsUserMessageTest extends AbstractTestNGSpringContextTests {
  @Autowired
  private UserHandler userHandler;

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Configuration
  public static class TestUserConfiguration {
    @Bean
    @Primary
    @Profile("integration-test")
    public UserHandler userHandler() {
      return new TestingUserHandler();
    }
  }

  @Test(timeOut = 3000)
  public void contextLoads() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN
    TestingUserHandler testingUserHandler = (TestingUserHandler) userHandler;

    // THEN
    User user = testingUserHandler.getUser();
    Assert.assertEquals(user.getEmail(), "lubos.krnac@gmail.com");
    Assert.assertEquals(user.getName(), "Lubos Krnac");
    User user1 = testingUserHandler.getUser();
    Assert.assertEquals(user1.getEmail(), "lubos.krnac@gmail.com");
    Assert.assertEquals(user1.getName(), "Lubos Krnac");
  }
}
