package net.lkrnac.book.eiws.chapter09;

import javax.sql.DataSource;

import net.lkrnac.book.eiws.chapter09.step.tea.AddTea;
import net.lkrnac.book.eiws.chapter09.step.tea.AddWater;
import net.lkrnac.book.eiws.chapter09.step.tea.BoilWater;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
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

  @Bean
  public Step boilWaterStep(StepBuilderFactory stepFactory, BoilWater boilWater) {
    return stepFactory.get("boilWaterStep").tasklet(boilWater).build();
  }

  @Bean
  public Step addTeaStep(StepBuilderFactory stepFactory, AddTea addTea) {
    return stepFactory.get("addTeaStep").tasklet(addTea).build();
  }

  @Bean
  public Step addWaterStep(StepBuilderFactory stepFactory, AddWater addWater) {
    return stepFactory.get("addWaterStep").tasklet(addWater).build();
  }

  @Bean
  public Job prepareTeaJob(JobBuilderFactory jobBuilderFactory,
      @Qualifier("boilWaterStep") Step boilWaterStep,
      @Qualifier("addTeaStep") Step addTeaStep,
      @Qualifier("addWaterStep") Step addWaterStep) {
    return jobBuilderFactory.get("prepareTeaJob")
        .flow(boilWaterStep)
        .next(addTeaStep)
        .next(addWaterStep)
        .end()
        .build();
  }
}
