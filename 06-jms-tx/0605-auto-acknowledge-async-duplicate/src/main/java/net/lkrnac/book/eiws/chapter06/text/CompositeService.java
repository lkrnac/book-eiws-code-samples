package net.lkrnac.book.eiws.chapter06.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompositeService {
  private SimpleService simpleService;

  @Autowired
  public CompositeService(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void processText(String message) {
    simpleService.processText(message);
  }
}
