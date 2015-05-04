package net.lkrnac.book.eiws.chapter04;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter04.client.ClientConfiguration;
import net.lkrnac.book.eiws.chapter04.client.UserInfoService;
import net.lkrnac.book.eiws.chapter04.model.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootApplication
@Import(ClientConfiguration.class)
public class RestClientAsyncApplication {
  public static void main(String... args) {
    SpringApplication.run(RestClientAsyncApplication.class, args);
  }

  @Autowired
  private UserInfoService userInfoService;

  @PostConstruct
  public void manualTest() {
    UserInfo userInfo = userInfoService.getUserInfo(0);
    log.info(userInfo.getUser().getName());
  }
}
