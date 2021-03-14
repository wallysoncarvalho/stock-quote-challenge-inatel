package info.wallyson.sqm.stockmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Component
public class StockManagerServicesImpl implements CheckStock, RegisterNotification {
  private final Logger LOG = LoggerFactory.getLogger(StockManagerServicesImpl.class);
  private final RestTemplate restTemplate;
  private final String STOCK_MANAGER_URL;

  public StockManagerServicesImpl(
      RestTemplate restTemplate, @Value("${stock-manager.url}") String stockManagerUlr) {
    this.restTemplate = restTemplate;
    this.STOCK_MANAGER_URL = stockManagerUlr;
  }

  @Cacheable("stock_id")
  @Override
  public boolean stockExists(String stockId) {
    var response =
        restTemplate.exchange(
            String.format("%s/stock/%s", STOCK_MANAGER_URL, stockId),
            HttpMethod.GET,
            null,
            String.class);

    return response.getStatusCode().is2xxSuccessful() && Objects.nonNull(response.getBody());
  }

  @Override
  public void register(String host, int port) {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode service = mapper.createObjectNode();
    service.put("host", host);
    service.put("port", port);

    try {
      var json = mapper.writeValueAsString(service);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
      var entity = new HttpEntity<String>(json, headers);

      var response =
          restTemplate.postForEntity(STOCK_MANAGER_URL + "/notification", entity, String.class);

      if (response.getStatusCode().is2xxSuccessful()) {
        LOG.info("Service registered on 'stock-manager'.");
      }

    } catch (JsonProcessingException e) {
      LOG.error("Error registering the service on 'stock-manager'!");
    }
  }
}
