package net.lkrnac.book.eiws.chapter09.step.tea;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddSugar implements Tasklet {
  private SimpleExecutablePoint simpleExecutableStep;
  private SugarCounter sugarCounter;

  @Autowired
  public AddSugar(SimpleExecutablePoint simpleExecutableStep,
      SugarCounter sugarCounter) {
    super();
    this.simpleExecutableStep = simpleExecutableStep;
    this.sugarCounter = sugarCounter;
  }

  @Override
  public RepeatStatus execute(StepContribution contribution,
      ChunkContext chunkContext) throws Exception {
    simpleExecutableStep.execute("Add one spoon of sugar");
    sugarCounter.setSpoonsCount(sugarCounter.getSpoonsCount() + 1);
    return (sugarCounter.getSpoonsCount() > 1) ? RepeatStatus.FINISHED
        : RepeatStatus.CONTINUABLE;
  }
}
