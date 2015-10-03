package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BatchApplication {
  public static void main(String[] args) throws Exception {
    ConfigurableApplicationContext context =
        SpringApplication.run(BatchApplication.class, args);
    JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);

    Job job = (Job) context.getBean("prepareTeaJob");
    jobLauncher.run(job, new JobParameters());
    jobLauncher.run(job, new JobParameters());
    jobLauncher.run(job, new JobParameters());

    Job jobNotRestarteble =
        (Job) context.getBean("prepareTeaJobNotRestartable");
    jobLauncher.run(jobNotRestarteble, new JobParameters());
    jobLauncher.run(jobNotRestarteble, new JobParameters());
    context.close();
  }
}
