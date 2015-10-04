package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.process.SimpleRecordProcessor;
import net.lkrnac.book.eiws.chapter09.read.SimpleRecordReader;
import net.lkrnac.book.eiws.chapter09.write.SimpleRecordWriter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
  @Bean
  public TaskExecutor customTaskExecutor() {
    ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
    threadPool.setCorePoolSize(10);
    return threadPool;
  }

  @Bean
  public Step simpleRecordsStep(StepBuilderFactory stepBuilderFactory,
      SimpleRecordReader simpleRecordReader,
      SimpleRecordProcessor simpleRecordProcessor,
      SimpleRecordWriter simpleRecordWriter,
      TaskExecutor customTaskExecutor) {
    return stepBuilderFactory.get("simpleRecordsStep")
        .<String, String> chunk(4)
        .reader(simpleRecordReader)
        .processor(simpleRecordProcessor)
        .writer(simpleRecordWriter)
        .taskExecutor(customTaskExecutor)
        .build();
  }

  @Bean
  public Job simpleRecordsJob(JobBuilderFactory jobBuilderFactory,
      Step simpleRecordsStep) {
    return jobBuilderFactory.get("simpleRecordsJob")
        .flow(simpleRecordsStep)
        .end()
        .build();
  }
}
