package net.lkrnac.book.eiws.chapter04.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//@Configuration
//@ComponentScan
public class RestAccessConfiguration {
  @Bean
  public RestTemplate restTemplate() {
    PoolingHttpClientConnectionManager cm =
        new PoolingHttpClientConnectionManager();
    // Increase max total connection to 200
    cm.setMaxTotal(200);
    // Increase default max connection per route to 20
    cm.setDefaultMaxPerRoute(20);
    // Increase max connections for localhost:80 to 50
    HttpHost localhost = new HttpHost("locahost", 80);
    cm.setMaxPerRoute(new HttpRoute(localhost), 50);

    CloseableHttpClient httpClient =
        HttpClients.custom().setConnectionManager(cm).build();

    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory(httpClient);
    requestFactory.setConnectionRequestTimeout(500);
    return new RestTemplate(requestFactory);
  }

  @Bean
  public ExecutorService executorService() {
    return Executors.newFixedThreadPool(10);
  }
}
