package net.lkrnac.book.eiws.chapter08.out;

import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class WriteServiceAnnotated {
  private WriteRepository writeRepository;

  @Autowired
  public WriteServiceAnnotated(WriteRepository writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  @ServiceActivator(inputChannel = "splittedChannel")
  public boolean writeAndIndicateSuccess(String message) {
    boolean result = writeRepository.persist(message) == 1;
    if ("messageFail".equals(message)) {
      return false;
    }
    return result;
  }
}
