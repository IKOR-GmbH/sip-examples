package de.ikor.sip.adapter.secured;

import de.ikor.sip.foundation.core.annotation.SIPIntegrationAdapter;
import org.springframework.boot.SpringApplication;

@SIPIntegrationAdapter
public class SIPApplication {
  public static void main(String[] args) {
    SpringApplication.run(SIPApplication.class, args);
  }
}
