package net.lkrnac.book.eiws.chapter09;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
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
    Job job = (Job) context.getBean("combinedJob");

    JobParameters jobParameters = createJobParameters(11);
    JobExecution execution = jobLauncher.run(job, jobParameters);
    log.info("Exit Status : {}", execution.getStatus());

    context.close();
  }

  public static JobParameters createJobParameters(long recordCountToProcess) {
    Map<String, JobParameter> jobParametersMap = new HashMap<>();
    JobParameter jobParameter = new JobParameter(recordCountToProcess);
    jobParametersMap.put("recordCountToProcess", jobParameter);
    JobParameters jobParameters = new JobParameters(jobParametersMap);
    return jobParameters;
  }
}
