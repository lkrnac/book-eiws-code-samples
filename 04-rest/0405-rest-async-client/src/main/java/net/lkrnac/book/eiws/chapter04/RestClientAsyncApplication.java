package net.lkrnac.book.eiws.chapter04;

import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter04.client.ClientConfiguration;
import net.lkrnac.book.eiws.chapter04.client.UserInfoService;
import net.lkrnac.book.eiws.chapter04.model.User;
import net.lkrnac.book.eiws.chapter04.model.UserInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

@Slf4j
public class RestClientAsyncApplication {
  public static void main(String... args) throws InterruptedException,
      ExecutionException {
    ConfigurableApplicationContext context =
        SpringApplication.run(ClientConfiguration.class, args);

    AsyncRestTemplate asyncRestTemplate =
        context.getBean(AsyncRestTemplate.class);
    UserInfoService userInfoService = context.getBean(UserInfoService.class);

    User user = new User();
    user.setName("Lubos");
    HttpEntity<User> httpRequest = new HttpEntity<>(user);
    ListenableFuture<ResponseEntity<User>> httpResponse =
        asyncRestTemplate.postForEntity("http://localhost:10405/users",
            httpRequest, User.class);
    httpResponse.get();

    UserInfo userInfo = userInfoService.getUserInfo(0);
    log.info(userInfo.getUser().getName());
    context.stop();
    context.close();
  }
}
