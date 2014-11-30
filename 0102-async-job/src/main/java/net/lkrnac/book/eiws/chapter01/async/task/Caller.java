package net.lkrnac.book.eiws.chapter01.async.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Caller {
  private final AsyncTask asyncTask;
  private final SimpleLogger logger;

  @Autowired
  public Caller(AsyncTask asyncTask, SimpleLogger logger) {
    this.asyncTask = asyncTask;
    this.logger = logger;
  }

  public void kickOffAsyncTasks(int execCount) throws InterruptedException {
    Collection<Future<String>> results = new ArrayList<>(execCount);

    long start = System.currentTimeMillis();
    for (int idx = 0; idx < execCount; idx++) {
      results.add(asyncTask.call(idx));
    }

    results.forEach(result -> {
      try {
        logger.log(result.get());
      } catch (InterruptedException | ExecutionException e) {
        logger.log(e.getLocalizedMessage());
      }
    });
    logger.log("Elapsed time: " + (System.currentTimeMillis() - start));
  }
}
