package one.x1f.sip.adapter.token;

import one.x1f.sip.foundation.core.annotation.SIPIntegrationAdapter;
import org.springframework.boot.SpringApplication;

@SIPIntegrationAdapter
public class SIPApplication {
  public static void main(String[] args) {
    SpringApplication.run(SIPApplication.class, args);
  }
}
