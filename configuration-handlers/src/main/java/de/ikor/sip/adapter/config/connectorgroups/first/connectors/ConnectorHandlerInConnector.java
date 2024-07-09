package de.ikor.sip.adapter.config.connectorgroups.first.connectors;

import de.ikor.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import de.ikor.sip.adapter.config.scenarios.definitions.DemoGenericHandlerScenario;
import de.ikor.sip.foundation.core.declarative.annonation.ConfigurationHandler;
import de.ikor.sip.foundation.core.declarative.annonation.InboundConnector;
import de.ikor.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import lombok.RequiredArgsConstructor;
import org.apache.camel.model.rest.RestDefinition;

/**
 * Inbound connector designed to demonstrate exception handling by connector level handler
 */
@InboundConnector(
        connectorId = "ConnectorHandlerInConnector",
        connectorGroup = "first",
        requestModel = String.class,
        responseModel = String.class,
        integrationScenario = DemoGenericHandlerScenario.ID)
@RequiredArgsConstructor
@ConfigurationHandler(ConnectorLevelErrorHandler.class)
public class ConnectorHandlerInConnector extends RestInboundConnectorBase {
    @Override
    protected void configureRest(RestDefinition restDefinition) {
        restDefinition.get("/exception/generic");
    }
}