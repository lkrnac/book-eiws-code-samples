package net.lkrnac.book.eiws.chapter06.text;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleService {
  private final SimpleRepository simpleRepository;

  @Autowired
  public SimpleService(SimpleRepository simpleRepository) {
    super();
    this.simpleRepository = simpleRepository;
  }

  public void processText(String text) {
    log.info("Process Message: {}", text);
    simpleRepository.persistText(text);
  }

  public boolean isProcessed(String text) {
    return simpleRepository.containsText(text);
  }
}
