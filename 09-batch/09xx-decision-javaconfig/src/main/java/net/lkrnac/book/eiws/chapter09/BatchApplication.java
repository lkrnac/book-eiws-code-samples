package net.lkrnac.book.eiws.chapter09;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
@SpringBootApplication
public class BatchApplication {
  public static void main(String[] args) throws Exception {
    GenericApplicationContext context =
        new AnnotationConfigApplicationContext(BatchApplication.class);

    JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
    Job job = (Job) context.getBean("prepareTeaJob");

    JobParameters jobParameters1 = createJobParameters("milk");
    JobExecution execution1 = jobLauncher.run(job, jobParameters1);
    log.info("Exit Status : {}", execution1.getStatus());

    JobParameters jobParameters2 = createJobParameters("");
    JobExecution execution2 = jobLauncher.run(job, jobParameters2);
    log.info("Exit Status : {}", execution2.getStatus());

    context.close();
  }

  public static JobParameters createJobParameters(String teaIngredient) {
    Map<String, JobParameter> jobParametersMap = new HashMap<>();
    jobParametersMap.put("teaIngredient", new JobParameter(teaIngredient));
    JobParameters jobParameters = new JobParameters(jobParametersMap);
    return jobParameters;
  }
}
