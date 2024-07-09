package de.ikor.sip.adapter.config.connectorgroups.second.connectors;

import de.ikor.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import de.ikor.sip.adapter.config.scenarios.definitions.DemoGenericHandlerScenario;
import de.ikor.sip.foundation.core.declarative.annonation.ConfigurationHandler;
import de.ikor.sip.foundation.core.declarative.annonation.OutboundConnector;
import de.ikor.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
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
