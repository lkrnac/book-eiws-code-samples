package net.lkrnac.book.eiws.chapter09.step.tea;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddTeaWithParameter implements Tasklet {
  private SimpleExecutablePoint simpleExecutableStep;

  @Autowired
  public AddTeaWithParameter(SimpleExecutablePoint simpleExecutableStep) {
    super();
    this.simpleExecutableStep = simpleExecutableStep;
  }

  @Override
  public RepeatStatus execute(StepContribution contribution,
      ChunkContext chunkContext) throws Exception {
    String sugarAmount = chunkContext.getStepContext().getStepExecution()
        .getJobParameters().getString("sugarAmount");
    String stepSuffix = (sugarAmount == null) ? "" : " with " + sugarAmount;
    simpleExecutableStep.execute("Add Tea" + stepSuffix);
    return RepeatStatus.FINISHED;
  }
}
