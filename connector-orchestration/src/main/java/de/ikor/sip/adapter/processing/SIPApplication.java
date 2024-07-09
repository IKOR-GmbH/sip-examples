package de.ikor.sip.adapter.processing;

import org.springframework.boot.SpringApplication;
import de.ikor.sip.foundation.core.annotation.SIPIntegrationAdapter;

@SIPIntegrationAdapter
public class SIPApplication {
    public static void main(String[] args) {
        SpringApplication.run(SIPApplication.class, args);
    }

}
