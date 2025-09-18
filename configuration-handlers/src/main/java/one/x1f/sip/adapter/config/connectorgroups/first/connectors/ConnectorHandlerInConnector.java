package one.x1f.sip.adapter.config.connectorgroups.first.connectors;

import one.x1f.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import one.x1f.sip.adapter.config.scenarios.definitions.DemoGenericHandlerScenario;
import one.x1f.sip.foundation.core.declarative.annotation.ConfigurationHandler;
import one.x1f.sip.foundation.core.declarative.annotation.InboundConnector;
import one.x1f.sip.foundation.core.declarative.annotation.connector.extension.RequestProcessor;
import one.x1f.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
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
    @RequestProcessor
    public void process(Exchange exchange) {
        exchange.getMessage().setBody("");
    }
}