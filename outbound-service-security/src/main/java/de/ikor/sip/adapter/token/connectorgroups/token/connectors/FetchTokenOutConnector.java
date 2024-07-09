package de.ikor.sip.adapter.token.connectorgroups.token.connectors;

import static de.ikor.sip.adapter.token.connectorgroups.token.config.KeycloakConfigurationProperties.*;
import static org.apache.camel.builder.Builder.simple;

import de.ikor.sip.adapter.token.connectorgroups.token.config.KeycloakConfigurationProperties;
import de.ikor.sip.adapter.token.scenarios.definitions.FetchTokenScenario;
import de.ikor.sip.foundation.core.declarative.annonation.OutboundConnector;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.RequestProcessor;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.ResponseProcessor;
import de.ikor.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
import de.ikor.sip.foundation.core.declarative.model.UnmarshallerDefinition;
import de.ikor.sip.foundation.core.declarative.orchestration.Orchestrator;
import de.ikor.sip.foundation.core.declarative.orchestration.connector.ConnectorOrchestrationInfo;
import de.ikor.sip.foundation.core.declarative.orchestration.connector.ConnectorOrchestrator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.RouteDefinition;

/** Outbound connector which fetches token from keycloak server */
@OutboundConnector(
    connectorGroup = "keycloak",
    integrationScenario = FetchTokenScenario.ID,
    requestModel = Object.class,
    responseModel = Object.class,
    connectorId = "FetchTokenOutConnector")
@RequiredArgsConstructor
public class FetchTokenOutConnector extends GenericOutboundConnectorBase {

  private final KeycloakConfigurationProperties configurationProperties;

  // Define external outbound endpoint
  @Override
  protected EndpointProducerBuilder defineOutgoingEndpoint() {
    return StaticEndpointBuilders.http("{{adapter.keycloak.uri}}")
        .httpMethod("POST")
        .bridgeEndpoint(true);
  }

  @ResponseProcessor
  public void processResponse(Exchange exchange) {
      Map<String, Object> body = exchange.getMessage().getBody(Map.class);
      exchange.getMessage().setHeader("Authorization",
              String.format("Bearer %s", body.get(ACCESS_TOKEN)));
  }

  @RequestProcessor
  public void processRequest(Exchange exchange) {
      exchange.getMessage().setHeader("Content-Type", "application/x-www-form-urlencoded");
      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put(CLIENT_ID, configurationProperties.getClientId());
      requestBody.put(GRANT_TYPE, configurationProperties.getGrantType());
      requestBody.put(USERNAME, configurationProperties.getUsername());
      requestBody.put(PASSWORD, configurationProperties.getPassword());
      exchange.getMessage().setBody(getRequestAsString(requestBody));
  }

  private String getRequestAsString(Map<String, Object> requestBody) {
    StringBuilder body = new StringBuilder();
    requestBody.forEach(
        (key, value) -> {
          if (body.length() > 0) {
            body.append("&");
          }
          body.append(key).append("=").append(value);
        });
    return body.toString();
  }

  @Override
  protected Optional<UnmarshallerDefinition> defineResponseUnmarshalling() {
    return Optional.of(UnmarshallerDefinition.forDataFormat(new JacksonDataFormat(Map.class)));
  }
}
