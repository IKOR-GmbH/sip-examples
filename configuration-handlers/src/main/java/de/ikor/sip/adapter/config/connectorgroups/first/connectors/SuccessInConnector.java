package de.ikor.sip.adapter.config.connectorgroups.first.connectors;

import de.ikor.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import de.ikor.sip.adapter.config.scenarios.definitions.DemonInterceptConfigurationScenario;
import de.ikor.sip.foundation.core.declarative.annonation.ConfigurationHandler;
import de.ikor.sip.foundation.core.declarative.annonation.InboundConnector;
import de.ikor.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import lombok.RequiredArgsConstructor;
import org.apache.camel.model.rest.RestDefinition;

/**
 * Inbound connector designed to demonstrate intercept configuration
 */
@InboundConnector(
        connectorId = "SuccessInConnector",
        connectorGroup = "first",
        requestModel = String.class,
        responseModel = String.class,
        integrationScenario = DemonInterceptConfigurationScenario.ID)
@RequiredArgsConstructor
@ConfigurationHandler(ConnectorLevelErrorHandler.class)
public class SuccessInConnector extends RestInboundConnectorBase {

    @Override
    protected void configureRest(RestDefinition restDefinition) {
        restDefinition.get("/success");
    }
}