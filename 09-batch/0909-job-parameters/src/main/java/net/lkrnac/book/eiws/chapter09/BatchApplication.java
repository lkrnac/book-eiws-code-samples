package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BatchApplication {
  public static void main(String[] args) throws Exception {
    ConfigurableApplicationContext context =
        SpringApplication.run(BatchApplication.class);

    JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
    Job job = (Job) context.getBean("prepareTeaJob");

    JobParameters jobParameters1 = createJobParameters("no sugar");
    JobExecution execution1 = jobLauncher.run(job, jobParameters1);
    log.info("Exit Status: {}", execution1.getStatus());

    JobParameters jobParameters2 = createJobParameters("two spoons of sugar");
    JobExecution execution2 = jobLauncher.run(job, jobParameters2);
    log.info("Exit Status: {}", execution2.getStatus());
    context.close();
  }

  public static JobParameters createJobParameters(String sugarAmountValue) {
    return new JobParametersBuilder()
        .addString("sugarAmount", sugarAmountValue)
        .toJobParameters();
  }
}
