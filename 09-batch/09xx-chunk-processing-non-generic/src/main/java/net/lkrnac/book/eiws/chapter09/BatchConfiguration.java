package net.lkrnac.book.eiws.chapter09;

import javax.sql.DataSource;

import net.lkrnac.book.eiws.chapter09.model.User;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Bean
  public FlatFileItemReader<User> fileItemReader() {
    FlatFileItemReader<User> flatFileItemReader =
        new FlatFileItemReader<User>();
    flatFileItemReader.setResource(new ClassPathResource("users.txt"));

    BeanWrapperFieldSetMapper<User> fieldSetMapper =
        new BeanWrapperFieldSetMapper<User>();
    fieldSetMapper.setTargetType(User.class);

    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(",");
    lineTokenizer.setNames(new String[] { "name", "email" });

    DefaultLineMapper<User> lineMapper = new DefaultLineMapper<User>();
    lineMapper.setFieldSetMapper(fieldSetMapper);
    lineMapper.setLineTokenizer(lineTokenizer);

    flatFileItemReader.setLineMapper(lineMapper);
    return flatFileItemReader;
  }

  @Bean
  public ItemWriter<User> jdbcItemWriter(DataSource dataSource) {
    JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<User>();
    writer
        .setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
    writer.setSql("insert into USERS (NAME, EMAIL) values (:name, :email)");
    writer.setDataSource(dataSource);
    return writer;
  }

  @Bean
  public Step simpleRecordsStep(StepBuilderFactory stepBuilderFactory,
      FlatFileItemReader<User> fileItemReader,
      UserRecordProcessor userRecordProcessor,
      JdbcBatchItemWriter<User> simpleRecordWriter) {
    return stepBuilderFactory.get("simpleRecordsStep")
        .<User, User> chunk(4)
        .reader(fileItemReader)
        .processor(userRecordProcessor)
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
