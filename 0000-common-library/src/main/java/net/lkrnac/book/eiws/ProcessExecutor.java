package net.lkrnac.book.eiws;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

/**
 * Class responsible to executing jar files.
 * 
 * @author Lubos Krnac
 *
 */
public class ProcessExecutor {
  /**
   * Runs executable jar is separate process and redirects console into "log"
   * file.
   * 
   * @param jarName
   *          name of the jar to run
   * @return started {@link Process} instance
   * @throws IOException
   *           if I/O error occurs
   */
  public final Process execute(String jarName) throws IOException {
    ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jarName);
    processBuilder.directory(new File("target/dependency"));
    File log = new File("process-executor.log");
    processBuilder.redirectErrorStream(true);
    processBuilder.redirectOutput(Redirect.appendTo(log));
    return processBuilder.start();
  }
}
