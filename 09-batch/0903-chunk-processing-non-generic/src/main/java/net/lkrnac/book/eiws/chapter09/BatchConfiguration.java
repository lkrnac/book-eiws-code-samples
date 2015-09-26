package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.model.User;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
  @Bean
  public Step simpleRecordsStep(StepBuilderFactory stepBuilderFactory,
      ItemReader<User> fileItemReader,
      ItemWriter<User> simpleRecordWriter) {
    return stepBuilderFactory.get("simpleRecordsStep")
        .<User, User> chunk(4)
        .reader(fileItemReader)
        .writer(simpleRecordWriter)
        .build();
  }

  @Bean
  public Job simpleRecordsJob(JobBuilderFactory jobBuilderFactory,
      Step simpleRecordsStep) {
    return jobBuilderFactory.get("simpleRecordsJob")
        .start(simpleRecordsStep)
        .build();
  }
}
