package net.lkrnac.book.eiws.chapter09.step.tea;

import net.lkrnac.book.eiws.chapter09.ProcessedRecordsCounter;
import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoilWaterStateful implements Tasklet {
  private SimpleExecutablePoint simpleExecutableStep;
  private ProcessedRecordsCounter recordsCounter;

  @Autowired
  public BoilWaterStateful(SimpleExecutablePoint simpleExecutableStep,
      ProcessedRecordsCounter recordsCounter) {
    super();
    this.simpleExecutableStep = simpleExecutableStep;
    this.recordsCounter = recordsCounter;
  }

  @Override
  public RepeatStatus execute(StepContribution contribution,
      ChunkContext chunkContext) throws Exception {
    simpleExecutableStep.execute("After we processed "
        + recordsCounter.getProcessedCount() + " records, let's have a tea");
    simpleExecutableStep.execute("Boil Water");
    return RepeatStatus.FINISHED;
  }
}
