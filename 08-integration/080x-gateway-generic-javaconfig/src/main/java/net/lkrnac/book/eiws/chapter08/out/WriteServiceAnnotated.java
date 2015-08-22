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

  @ServiceActivator(inputChannel = "gatewayChannel")
  public boolean writeAndIndicateSuccess(String message) {
    return writeRepository.persist(message) == 1;
  }
}
