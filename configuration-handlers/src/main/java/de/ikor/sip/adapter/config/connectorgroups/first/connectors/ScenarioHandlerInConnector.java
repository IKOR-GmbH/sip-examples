package de.ikor.sip.adapter.config.connectorgroups.first.connectors;

import de.ikor.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import de.ikor.sip.adapter.config.scenarios.definitions.DemoBadHostScenario;
import de.ikor.sip.foundation.core.declarative.annonation.ConfigurationHandler;
import de.ikor.sip.foundation.core.declarative.annonation.InboundConnector;
import de.ikor.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import lombok.RequiredArgsConstructor;
import org.apache.camel.model.rest.RestDefinition;

/**
 * Inbound connector designed to demonstrate exception handling by scenario level handler
 */
@InboundConnector(
        connectorId = "ScenarioHandlerInConnector",
        connectorGroup = "first",
        requestModel = String.class,
        responseModel = String.class,
        integrationScenario = DemoBadHostScenario.ID)
@RequiredArgsConstructor
@ConfigurationHandler(ConnectorLevelErrorHandler.class)
public class ScenarioHandlerInConnector extends RestInboundConnectorBase {

    // Define REST endpoint
    @Override
    protected void configureRest(RestDefinition restDefinition) {
        restDefinition.get("/exception/scenario");
    }
}