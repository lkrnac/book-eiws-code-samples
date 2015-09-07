package net.lkrnac.book.eiws.chapter09;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import net.lkrnac.book.eiws.chapter09.process.SimpleRecordProcessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
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
  public ItemWriter<String> jdbcItemWriter(DataSource dataSource) {
    JdbcBatchItemWriter<String> writer = new JdbcBatchItemWriter<String>();
    writer
        .setItemPreparedStatementSetter(new ItemPreparedStatementSetter<String>() {
          @Override
          public void setValues(String item, PreparedStatement ps) throws
              SQLException {
            ps.setString(1, item);
          }
        });
    writer.setSql("insert into TEXT_TABLE values (?)");
    writer.setDataSource(dataSource);
    return writer;
  }

  @Bean
  public Step simpleRecordsStep(StepBuilderFactory stepBuilderFactory,
      FlatFileItemReader<String> fileItemReader,
      SimpleRecordProcessor simpleRecordProcessor,
      JdbcBatchItemWriter<String> simpleRecordWriter) {
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
