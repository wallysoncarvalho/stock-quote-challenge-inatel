package info.wallyson.sqm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BeanConfig {

  @Bean
  public RestTemplate registerRestTemplateBean() {
    return new RestTemplate();
  }
}
