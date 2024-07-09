package de.ikor.sip.adapter.token.connectorgroups.token.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("adapter.keycloak")
public class KeycloakConfigurationProperties {
  public static final String GRANT_TYPE = "grant_type";
  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";
  public static final String CLIENT_ID = "client_id";
  public static final String ACCESS_TOKEN = "access_token";

  private String clientId;
  private String grantType;
  private String username;
  private String password;
}
