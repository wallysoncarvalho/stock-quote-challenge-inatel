package info.wallyson.sqm.event;

import info.wallyson.sqm.stockmanager.RegisterNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class CustomAppReadyEvent implements ApplicationListener<ApplicationReadyEvent> {
  private final Logger LOG = LoggerFactory.getLogger(CustomAppReadyEvent.class);
  private final RegisterNotification registerNotification;
  private final ApplicationContext applicationContext;

  public CustomAppReadyEvent(
      RegisterNotification registerNotification, ApplicationContext applicationContext) {
    this.registerNotification = registerNotification;
    this.applicationContext = applicationContext;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    try {
      var ip = InetAddress.getLocalHost().getHostAddress();
      var port =
          applicationContext
              .getBean(Environment.class)
              .getProperty("server.port", Integer.class, 8081);

      registerNotification.register(ip, port);

    } catch (UnknownHostException e) {
      LOG.error("Failed to get host url!");
    }
  }
}
