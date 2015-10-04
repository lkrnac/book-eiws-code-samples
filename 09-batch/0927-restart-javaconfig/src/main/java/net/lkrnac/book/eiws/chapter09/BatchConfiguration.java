package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.tea.AddTea;
import net.lkrnac.book.eiws.chapter09.step.tea.AddWater;
import net.lkrnac.book.eiws.chapter09.step.tea.BoilWater;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
  @Bean
  public Step boilWaterStep(StepBuilderFactory stepFactory, BoilWater boilWater) {
    return stepFactory.get("boilWaterStep")
        .tasklet(boilWater)
        .allowStartIfComplete(true)
        .build();
  }

  @Bean
  public Step addTeaStep(StepBuilderFactory stepFactory, AddTea addTea) {
    return stepFactory.get("addTeaStep")
        .tasklet(addTea)
        .allowStartIfComplete(true)
        .startLimit(2)
        .build();
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
        .start(boilWaterStep)
        .next(addTeaStep)
        .next(addWaterStep)
        .build();
  }

  @Bean
  public Job prepareTeaJobNotRestartable(JobBuilderFactory jobBuilderFactory,
      @Qualifier("boilWaterStep") Step boilWaterStep,
      @Qualifier("addTeaStep") Step addTeaStep,
      @Qualifier("addWaterStep") Step addWaterStep) {
    Job job = jobBuilderFactory.get("prepareTeaJobNotRestartable")
        .start(boilWaterStep)
        .next(addTeaStep)
        .next(addWaterStep)
        .preventRestart()
        .build();
    return job;
  }
}
