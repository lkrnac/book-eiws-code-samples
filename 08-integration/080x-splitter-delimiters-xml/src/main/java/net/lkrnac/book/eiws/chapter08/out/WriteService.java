package net.lkrnac.book.eiws.chapter08.out;

import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

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

  public boolean writeAndIndicateSuccess(String message) {
    boolean result = writeRepository.persist(message) == 1;
    if ("messageFail".equals(message)) {
      return false;
    }
    return result;
  }
}
