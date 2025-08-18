package one.x1f.sip.adapter.token.connectorgroups.backend.connectors;

import static org.apache.camel.Exchange.HTTP_PATH;

import one.x1f.sip.adapter.token.scenarios.definitions.CallSecuredServiceScenario;
import one.x1f.sip.adapter.token.scenarios.models.response.NobelPrizeResponse;
import one.x1f.sip.foundation.core.declarative.annotation.OutboundConnector;
import one.x1f.sip.foundation.core.declarative.annotation.connector.extension.RequestProcessor;
import one.x1f.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
import one.x1f.sip.foundation.core.declarative.model.UnmarshallerDefinition;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;

/** Outbound connector which calls secured demo adapter */
@OutboundConnector(
    connectorGroup = "backend",
    integrationScenario = CallSecuredServiceScenario.ID,
    requestModel = Object.class,
    responseModel = NobelPrizeResponse.class,
    connectorId = "SecuredEndpointOutConnector")
public class SecuredEndpointOutConnector extends GenericOutboundConnectorBase {
  @Override
  protected EndpointProducerBuilder defineOutgoingEndpoint() {
    return StaticEndpointBuilders.http(
            "http", "{{adapter.external.uri}}/${header.category}/${header.year}")
        .bridgeEndpoint(true);
  }

  @RequestProcessor
  public void processRequest(Exchange exchange) {
    exchange.getMessage().removeHeader(HTTP_PATH);
  }

  @Override
  protected Optional<UnmarshallerDefinition> defineResponseUnmarshalling() {
    return Optional.of(
        UnmarshallerDefinition.forClause(
            unmarshaller -> unmarshaller.json(NobelPrizeResponse.class)));
  }
}
