package net.lkrnac.book.eiws.chapter09;

import javax.sql.DataSource;

import net.lkrnac.book.eiws.chapter09.model.User;

import org.springframework.batch.item.ItemReader;
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
public class ChunkConfiguration {
  @Bean
  public ItemReader<User> fileItemReader() {
    FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
    flatFileItemReader.setResource(new ClassPathResource("users.txt"));

    BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(User.class);

    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(",");
    lineTokenizer.setNames(new String[] { "name", "email" });

    DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
    lineMapper.setFieldSetMapper(fieldSetMapper);
    lineMapper.setLineTokenizer(lineTokenizer);

    flatFileItemReader.setLineMapper(lineMapper);
    return flatFileItemReader;
  }

  @Bean
  public ItemWriter<User> jdbcItemWriter(DataSource dataSource) {
    JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
    writer.setItemSqlParameterSourceProvider(
        new BeanPropertyItemSqlParameterSourceProvider<>());
    writer.setSql("insert into USERS (NAME, EMAIL) values (:name, :email)");
    writer.setDataSource(dataSource);
    return writer;
  }
}
