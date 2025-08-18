package one.x1f.sip.adapter.config.connectorgroups.first.connectors;

import one.x1f.sip.adapter.config.config.exception.ConnectorLevelErrorHandler;
import one.x1f.sip.adapter.config.scenarios.definitions.DemonInterceptConfigurationScenario;
import one.x1f.sip.foundation.core.declarative.annotation.ConfigurationHandler;
import one.x1f.sip.foundation.core.declarative.annotation.InboundConnector;
import one.x1f.sip.foundation.core.declarative.annotation.connector.extension.RequestProcessor;
import one.x1f.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
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

    @RequestProcessor
    public void process(Exchange exchange) {
        exchange.getMessage().setBody("");
    }
}