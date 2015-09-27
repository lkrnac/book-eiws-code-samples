package net.lkrnac.book.eiws.chapter09;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class BatchApplication {
  public static void main(String[] args) throws Exception {
    ClassPathResource batchConfig = new ClassPathResource("batch-config.xml");
    ClassPathResource batchBeansConfig = new ClassPathResource("batch-beans-config.xml");
    GenericApplicationContext context = new GenericXmlApplicationContext(
        batchConfig, batchBeansConfig);

    JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
    Job job = (Job) context.getBean("prepareTeaJob");
    JobExecution execution = jobLauncher.run(job, new JobParameters());
    log.info("Exit Status: {}", execution.getStatus());
    context.close();
  }
}
