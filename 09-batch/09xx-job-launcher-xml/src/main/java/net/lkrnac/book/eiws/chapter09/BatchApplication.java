package net.lkrnac.book.eiws.chapter09;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class BatchApplication {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext context =
        new GenericXmlApplicationContext(new ClassPathResource(
            "batch-config.xml"));

    JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
    Job job = (Job) context.getBean("prepareTeaJob");

    try {
      JobExecution execution = jobLauncher.run(job, new JobParameters());
      log.info("Exit Status : {}", execution.getStatus());
    } catch (Exception e) {
      log.error("Error occured", e);
    }
  }
}
