package de.ikor.sip.adapter.config.connectorgroups.second.connectors;

import de.ikor.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import de.ikor.sip.adapter.config.config.exception.ErrorHandlerProcessor;
import de.ikor.sip.adapter.config.scenarios.definitions.DemoConcreteHandlerScenario;
import de.ikor.sip.foundation.core.declarative.annonation.ConfigurationHandler;
import de.ikor.sip.foundation.core.declarative.annonation.ConnectorExceptionHandler;
import de.ikor.sip.foundation.core.declarative.annonation.OutboundConnector;
import de.ikor.sip.foundation.core.declarative.configuration.ConnectorOnExceptionDefinition;
import de.ikor.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;

/**
* Outbound connector which simulates IllegalArgumentException.
* It will be inside the connector itself
*/
@OutboundConnector(
    connectorGroup = "second",
    integrationScenario = DemoConcreteHandlerScenario.ID,
    requestModel = String.class,
    responseModel = String.class,
    connectorId = "ConcreteHandlerOutConnector")
@ConfigurationHandler(ConnectorLevelErrorHandler.class)
public class ConcreteHandlerOutConnector extends GenericOutboundConnectorBase {
  @Override
  protected EndpointProducerBuilder defineOutgoingEndpoint() {
    return StaticEndpointBuilders.http("doesnotexist");
  }

  // Concrete exception handler which will take priority over other defined configurations
  @ConnectorExceptionHandler(IllegalArgumentException.class)
  public ConnectorOnExceptionDefinition handleIllegalArgumentException(){
    return onExceptionDefinition -> onExceptionDefinition
            .process(new ErrorHandlerProcessor(this.getClass()))
            .handled(true);
  }
}
