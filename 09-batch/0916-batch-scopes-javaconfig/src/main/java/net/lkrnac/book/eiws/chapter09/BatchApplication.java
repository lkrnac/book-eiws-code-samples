package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BatchApplication {
  public static void main(String[] args) throws Exception {
    ConfigurableApplicationContext context =
        SpringApplication.run(BatchApplication.class);

    JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
    Job job = (Job) context.getBean("combinedJob");

    jobLauncher.run(job, createJobParameters(3));
    jobLauncher.run(job, createJobParameters(2));
    context.close();
  }

  public static JobParameters createJobParameters(long recordCountToProcess) {
    return new JobParametersBuilder()
        .addLong("recordCountToProcess", recordCountToProcess)
        .toJobParameters();
  }
}
