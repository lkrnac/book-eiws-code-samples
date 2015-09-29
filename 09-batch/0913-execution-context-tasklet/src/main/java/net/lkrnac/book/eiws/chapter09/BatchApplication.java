package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class BatchApplication {
  public static void main(String[] args) throws Exception {
    GenericApplicationContext context =
        new AnnotationConfigApplicationContext(BatchApplication.class);

    JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
    Job job = (Job) context.getBean("prepareTeaJob");

    jobLauncher.run(job, new JobParameters());
    jobLauncher.run(job, new JobParameters());
    jobLauncher.run(job, new JobParameters());
    context.close();
  }
}
