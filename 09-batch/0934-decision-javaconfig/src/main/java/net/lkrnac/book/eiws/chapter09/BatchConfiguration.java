package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.tea.AddMilk;
import net.lkrnac.book.eiws.chapter09.step.tea.AddTea;
import net.lkrnac.book.eiws.chapter09.step.tea.AddWater;
import net.lkrnac.book.eiws.chapter09.step.tea.BoilWater;

import org.springframework.batch.core.ExitStatus;
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
  public Step addWaterStep(StepBuilderFactory stepFactory, AddWater addWater) {
    return stepFactory.get("addWaterStep").tasklet(addWater).build();
  }

  @Bean
  public Step addMilkStep(StepBuilderFactory stepFactory, AddMilk addMilk) {
    return stepFactory.get("addMilkStep").tasklet(addMilk).build();
  }

  @Bean
  public Job prepareTeaJob(JobBuilderFactory jobBuilderFactory,
      @Qualifier("boilWaterStep") Step boilWaterStep,
      @Qualifier("addTeaStep") Step addTeaStep,
      @Qualifier("addWaterStep") Step addWaterStep,
      @Qualifier("addMilkStep") Step addMilkStep,
      TeaIngredientDecider teaIngredientDecider) {
    //@formatter:off
    return jobBuilderFactory.get("prepareTeaJob")
        .start(boilWaterStep)
        .next(addTeaStep)
        .next(addWaterStep)
        .next(teaIngredientDecider)
          .on("milk")
            .to(addMilkStep)
            .from(teaIngredientDecider)
          .on(ExitStatus.COMPLETED.getExitCode())
            .end()
          .build()
        .build();
    //@formatter:on
  }
}
