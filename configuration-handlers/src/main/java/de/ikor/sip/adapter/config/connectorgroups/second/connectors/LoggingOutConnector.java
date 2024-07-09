package de.ikor.sip.adapter.config.connectorgroups.second.connectors;

import de.ikor.sip.adapter.config.scenarios.definitions.DemonInterceptConfigurationScenario;
import de.ikor.sip.foundation.core.declarative.annonation.OutboundConnector;
import de.ikor.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;

/**
 * Dummy outbound connector for logging
 */
@OutboundConnector(
    connectorGroup = "second",
    integrationScenario = DemonInterceptConfigurationScenario.ID,
    requestModel = String.class,
    responseModel = String.class,
    connectorId = "LoggingOutConnector")
public class LoggingOutConnector extends GenericOutboundConnectorBase {
  @Override
  protected EndpointProducerBuilder defineOutgoingEndpoint() {
    return StaticEndpointBuilders.log("out");
  }
}
