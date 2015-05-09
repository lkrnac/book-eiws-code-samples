package net.lkrnac.book.eiws.chapter04.client;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
public class ClientConfiguration {
  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new ResponseErrorHandler() {

      @Override
      public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        return statusCode.is5xxServerError();
      }

      @Override
      public void handleError(ClientHttpResponse response) throws IOException {
        throw new CustomException(response.getStatusText());
      }
    });
    return restTemplate;
  }
}
