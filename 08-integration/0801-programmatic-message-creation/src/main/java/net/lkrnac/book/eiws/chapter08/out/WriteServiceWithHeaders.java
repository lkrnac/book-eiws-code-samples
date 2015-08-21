package net.lkrnac.book.eiws.chapter08.out;

import net.lkrnac.book.eiws.chapter08.out.WriteRepositoryWithHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class WriteServiceWithHeaders {
  private WriteRepositoryWithHeaders writeRepository;

  @Autowired
  public WriteServiceWithHeaders(WriteRepositoryWithHeaders writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  public void write(String message, @Header("simpleHeader") String simpleHeader) {
    writeRepository.writeWithHeader(message, simpleHeader);
  }
}
