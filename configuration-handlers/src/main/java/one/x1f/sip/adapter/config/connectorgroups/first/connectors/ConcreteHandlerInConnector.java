package one.x1f.sip.adapter.config.connectorgroups.first.connectors;

import one.x1f.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import one.x1f.sip.adapter.config.scenarios.definitions.DemoConcreteHandlerScenario;
import one.x1f.sip.foundation.core.declarative.annotation.ConfigurationHandler;
import one.x1f.sip.foundation.core.declarative.annotation.InboundConnector;
import one.x1f.sip.foundation.core.declarative.annotation.connector.extension.RequestProcessor;
import one.x1f.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.model.rest.RestDefinition;

/**
 * Inbound connector designed to demonstrate exception handling on the most fine-grained level
 */
@InboundConnector(
        connectorId = "ConcreteHandlerInConnector",
        connectorGroup = "first",
        requestModel = String.class,
        responseModel = String.class,
        integrationScenario = DemoConcreteHandlerScenario.ID)
@RequiredArgsConstructor
@ConfigurationHandler(ConnectorLevelErrorHandler.class)
public class ConcreteHandlerInConnector extends RestInboundConnectorBase {

    @Override
    protected void configureRest(RestDefinition restDefinition) {
        restDefinition.get("/exception/concrete");
    }

    @RequestProcessor
    public void process(Exchange exchange) {
        exchange.getMessage().setBody("");
    }
}