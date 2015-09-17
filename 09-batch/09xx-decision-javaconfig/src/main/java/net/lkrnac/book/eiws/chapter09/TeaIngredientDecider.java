package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@Component
public class TeaIngredientDecider implements JobExecutionDecider {
  @Override
  public FlowExecutionStatus decide(JobExecution jobExecution,
      StepExecution stepExecution) {
    String teaIngredient =
        jobExecution.getJobParameters().getString("teaIngredient");
    return ("milk".equals(teaIngredient))
        ? new FlowExecutionStatus(teaIngredient)
        : FlowExecutionStatus.COMPLETED;
  }
}
