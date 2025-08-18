package one.x1f.sip.adapter.config.connectorgroups.second.connectors;

import one.x1f.sip.adapter.config.config.exception.ScenarioLevelErrorHandler;
import one.x1f.sip.adapter.config.scenarios.definitions.DemoBadHostScenario;
import one.x1f.sip.foundation.core.declarative.annotation.OutboundConnector;
import one.x1f.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;

/**
 * Outbound connector which simulates UnknownHostException.
 * It will be handled by {@link ScenarioLevelErrorHandler}
 */
@OutboundConnector(
    connectorGroup = "second",
    integrationScenario = DemoBadHostScenario.ID,
    requestModel = String.class,
    responseModel = String.class,
    connectorId = "BadHostOutConnector")
public class BadHostOutConnector extends GenericOutboundConnectorBase {
  @Override
  protected EndpointProducerBuilder defineOutgoingEndpoint() {
    return StaticEndpointBuilders.http("out").bridgeEndpoint(true);
  }
}
