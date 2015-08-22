package net.lkrnac.book.eiws.chapter08.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriteService {
  private WriteRepository writeRepository;

  @Autowired
  public WriteService(WriteRepository writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  public void write(String message) {
    writeRepository.persist(message);
  }

  public boolean writeAndIndicateSuccess(String message) {
    return writeRepository.persist(message) == 1;
  }
}
