package net.lkrnac.book.eiws.chapter02.rmi.spring;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class ProcessExecutor {
  public Process execute(String jarName) throws IOException {
    Process p = null;
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", jarName);
    pb.directory(new File("target/dependency"));
    File log = new File("log");
    pb.redirectErrorStream(true);
    pb.redirectOutput(Redirect.appendTo(log));
    p = pb.start();
    return p;
  }
}
