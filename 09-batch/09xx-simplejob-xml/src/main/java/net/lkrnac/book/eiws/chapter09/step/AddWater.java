package net.lkrnac.book.eiws.chapter09.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddWater implements Tasklet {
  private SimpleExecutableStep simpleExecutableStep;

  @Autowired
  public AddWater(SimpleExecutableStep simpleExecutableStep) {
    super();
    this.simpleExecutableStep = simpleExecutableStep;
  }

  @Override
  public RepeatStatus execute(StepContribution contribution,
      ChunkContext chunkContext) throws Exception {
    simpleExecutableStep.executeStep("Add Water");
    return RepeatStatus.FINISHED;
  }
}
