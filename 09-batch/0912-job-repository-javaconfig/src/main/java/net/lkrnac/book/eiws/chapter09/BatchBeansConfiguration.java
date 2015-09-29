package net.lkrnac.book.eiws.chapter09;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchBeansConfiguration {
  @Bean
  public AbstractPlatformTransactionManager transactionManager() {
    return new ResourcelessTransactionManager();
  }

  @Bean
  public DataSource db() {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    builder.setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:/org/springframework/batch/core/schema-h2.sql");
    return builder.build();
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public JobRepository jobRepository(
      AbstractPlatformTransactionManager transactionManager,
      DataSource dataSource) throws Exception {
    JobRepositoryFactoryBean jobRepositoryFactory =
        new JobRepositoryFactoryBean();
    jobRepositoryFactory.setTransactionManager(transactionManager);
    jobRepositoryFactory.setDataSource(dataSource);
    jobRepositoryFactory.setDatabaseType("h2");
    return jobRepositoryFactory.getObject();
  }

  @Bean
  public JobLauncher jobLauncher(JobRepository jobRepository) {
    SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
    simpleJobLauncher.setJobRepository(jobRepository);
    return simpleJobLauncher;
  }
}
