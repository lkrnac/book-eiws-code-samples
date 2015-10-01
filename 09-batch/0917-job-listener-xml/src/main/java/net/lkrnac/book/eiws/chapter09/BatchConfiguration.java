package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableBatchProcessing
@ImportResource("classpath:batch-config.xml")
public class BatchConfiguration {
}
