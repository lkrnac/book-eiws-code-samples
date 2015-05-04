package net.lkrnac.book.eiws.chapter04.client;

import java.util.Collection;

import net.lkrnac.book.eiws.chapter04.model.User;
import net.lkrnac.book.eiws.chapter04.model.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

@Component
public class UserInfoService {
  private static final Logger LOGGER = LoggerFactory
      .getLogger(UserInfoService.class);

  private static final String URL = "http://localhost:10405/users";
  private AsyncRestTemplate asyncRestTemplate;
  private UserActionsRepository userActionsRepository;

  @Autowired
  public UserInfoService(AsyncRestTemplate asyncRestTemplate,
      UserActionsRepository userActionsRepository) {
    super();
    this.asyncRestTemplate = asyncRestTemplate;
    this.userActionsRepository = userActionsRepository;
  }

  public UserInfo getUserInfo(int identifier) {
    final long start = System.currentTimeMillis();

    ListenableFuture<ResponseEntity<User>> futureResult =
        getUserAsync(identifier, start);

    Collection<String> userActions =
        userActionsRepository.getUserActions(identifier);
    logElapsedTime("User Actions repository", start);

    User user = null;
    try {
      user = futureResult.get().getBody();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }

    logElapsedTime("Overall", start);
    return new UserInfo(user, userActions);
  }

  private ListenableFuture<ResponseEntity<User>> getUserAsync(int identifier,
      final long start) {
    ListenableFuture<ResponseEntity<User>> futureResult =
        asyncRestTemplate.getForEntity(URL + "/" + identifier, User.class);

    futureResult
        .addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
          @Override
          public void onSuccess(ResponseEntity<User> result) {
            logElapsedTime("User Service", start);
          }

          @Override
          public void onFailure(Throwable ex) {
            logElapsedTime("User Service", start);
          }
        });
    return futureResult;
  }

  private void logElapsedTime(String messagePrefix, long start) {
    LOGGER.info("{} call took {} ms.", messagePrefix,
        System.currentTimeMillis() - start);
  }
}
