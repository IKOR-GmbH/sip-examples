package de.ikor.sip.adapter.config.connectorgroups.second.connectors;

import de.ikor.sip.adapter.config.config.exception.ScenarioLevelErrorHandler;
import de.ikor.sip.adapter.config.scenarios.definitions.DemoBadHostScenario;
import de.ikor.sip.foundation.core.declarative.annonation.OutboundConnector;
import de.ikor.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
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
