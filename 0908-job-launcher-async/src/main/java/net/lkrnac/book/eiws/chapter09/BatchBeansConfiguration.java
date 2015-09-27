package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

@Configuration
public class BatchBeansConfiguration {
  @Bean
  public AbstractPlatformTransactionManager transactionManager() {
    return new ResourcelessTransactionManager();
  }

  @Bean
  public JobRepository jobRepository(
      AbstractPlatformTransactionManager transactionManager) throws Exception {
    return (new MapJobRepositoryFactoryBean(transactionManager)).getObject();
  }

  @Bean
  public TaskExecutor customTaskExecutor() {
    ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
    threadPool.setCorePoolSize(10);
    return threadPool;
  }

  @Bean
  public JobLauncher jobLauncher(JobRepository jobRepository, TaskExecutor taskExecutor) {
    SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
    simpleJobLauncher.setJobRepository(jobRepository);
    simpleJobLauncher.setTaskExecutor(taskExecutor);
    return simpleJobLauncher;
  }
}
