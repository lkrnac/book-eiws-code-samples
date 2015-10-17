package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterChunkError;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

@Component
public class SimpleChunkListener {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public SimpleChunkListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @BeforeChunk
  public void beforeChunk(ChunkContext context) {
    executablePoint.execute("Starting chunk: " + context);
  }

  @AfterChunk
  public void afterChunk(ChunkContext context) {
    executablePoint.execute("After chunk: " + context);
  }

  @AfterChunkError
  public void afterChunkError(ChunkContext context) {
    executablePoint.execute("Error occured in chunk: " + context);
  }
}
