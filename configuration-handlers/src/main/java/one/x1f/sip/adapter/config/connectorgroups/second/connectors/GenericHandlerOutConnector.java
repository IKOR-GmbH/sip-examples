package one.x1f.sip.adapter.config.connectorgroups.second.connectors;

import one.x1f.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import one.x1f.sip.adapter.config.scenarios.definitions.DemoGenericHandlerScenario;
import one.x1f.sip.foundation.core.declarative.annotation.ConfigurationHandler;
import one.x1f.sip.foundation.core.declarative.annotation.OutboundConnector;
import one.x1f.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;

/**
 * Outbound connector which simulates IllegalArgumentException.
 * It will be handled by {@link ConnectorLevelErrorHandler}
 */
@OutboundConnector(
    connectorGroup = "second",
    integrationScenario = DemoGenericHandlerScenario.ID,
    requestModel = String.class,
    responseModel = String.class,
    connectorId = "GenericHandlerOutConnector")
@ConfigurationHandler(ConnectorLevelErrorHandler.class)
public class GenericHandlerOutConnector extends GenericOutboundConnectorBase {
  @Override
  protected EndpointProducerBuilder defineOutgoingEndpoint() {
    return StaticEndpointBuilders.http("doesnotexist");
  }

}
