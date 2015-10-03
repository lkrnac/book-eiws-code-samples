package net.lkrnac.book.eiws.chapter09;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class BatchApplication {
  public static void main(String[] args) throws Exception {
    ConfigurableApplicationContext context =
        SpringApplication.run(BatchApplication.class, args);

    JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
    Job job = (Job) context.getBean("prepareTeaJob");

    JobExecution execution;

    execution = jobLauncher.run(job, new JobParameters());
    log.info("Exit Status: {}", execution.getStatus());
    execution = jobLauncher.run(job, new JobParameters());
    log.info("Exit Status: {}", execution.getStatus());
    execution = jobLauncher.run(job, new JobParameters());
    log.info("Exit Status: {}", execution.getStatus());

    Job jobNotRestarteble =
        (Job) context.getBean("prepareTeaJobNotRestartable");

    execution = jobLauncher.run(jobNotRestarteble, new JobParameters());
    log.info("Exit Status: {}", execution.getStatus());
    execution = jobLauncher.run(jobNotRestarteble, new JobParameters());
    log.info("Exit Status: {}", execution.getStatus());

    context.close();

  }
}
