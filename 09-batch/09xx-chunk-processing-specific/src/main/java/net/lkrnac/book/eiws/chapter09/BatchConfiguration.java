package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.process.SimpleRecordProcessor;
import net.lkrnac.book.eiws.chapter09.write.SimpleRecordWriter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Bean
  public FlatFileItemReader<String> fileItemReader() {
    FlatFileItemReader<String> flatFileItemReader =
        new FlatFileItemReader<String>();
    flatFileItemReader.setResource(new ClassPathResource("records.txt"));
    flatFileItemReader.setLineMapper(new PassThroughLineMapper());
    return flatFileItemReader;
  }

  @Bean
  public Step simpleRecordsStep(StepBuilderFactory stepBuilderFactory,
      FlatFileItemReader<String> fileItemReader,
      SimpleRecordProcessor simpleRecordProcessor,
      SimpleRecordWriter simpleRecordWriter) {
    return stepBuilderFactory.get("simpleRecordsStep")
        .<String, String> chunk(4)
        .reader(fileItemReader)
        .processor(simpleRecordProcessor)
        .writer(simpleRecordWriter)
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
