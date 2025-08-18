package one.x1f.sip.adapter.config.connectorgroups.first.connectors;

import one.x1f.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import one.x1f.sip.adapter.config.scenarios.definitions.DemoBadHostScenario;
import one.x1f.sip.foundation.core.declarative.annotation.ConfigurationHandler;
import one.x1f.sip.foundation.core.declarative.annotation.InboundConnector;
import one.x1f.sip.foundation.core.declarative.annotation.connector.extension.RequestProcessor;
import one.x1f.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
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

    @RequestProcessor
    public void process(Exchange exchange) {
        exchange.getMessage().setBody("");
    }
}