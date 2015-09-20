package net.lkrnac.book.eiws.chapter09.copy;

import net.lkrnac.book.eiws.chapter09.step.tea.AddSugar;
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
    return stepFactory.get("boilWaterStep").tasklet(boilWater).build();
  }

  @Bean
  public Step addTeaStep(StepBuilderFactory stepFactory, AddTea addTea) {
    return stepFactory.get("addTeaStep").tasklet(addTea).build();
  }

  @Bean
  public Step addSugarStep(StepBuilderFactory stepFactory, AddSugar addSugar) {
    return stepFactory.get("addSugarStep").tasklet(addSugar).build();
  }

  @Bean
  public Step addWaterStep(StepBuilderFactory stepFactory, AddWater addWater) {
    return stepFactory.get("addWaterStep").tasklet(addWater).build();
  }

  @Bean
  public Job prepareTeaJob(JobBuilderFactory jobBuilderFactory,
      @Qualifier("boilWaterStep") Step boilWaterStep,
      @Qualifier("addTeaStep") Step addTeaStep,
      @Qualifier("addSugarStep") Step addSugarStep,
      @Qualifier("addWaterStep") Step addWaterStep) {
    return jobBuilderFactory.get("prepareTeaJob")
        .flow(boilWaterStep)
        .next(addTeaStep)
        .next(addSugarStep)
        .next(addWaterStep)
        .end()
        .build();
  }
}
