package one.x1f.sip.adapter.config.connectorgroups.second.connectors;

import one.x1f.sip.adapter.config.scenarios.definitions.DemonInterceptConfigurationScenario;
import one.x1f.sip.foundation.core.declarative.annotation.OutboundConnector;
import one.x1f.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
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
