package de.ikor.sip.adapter.processing.connectorgroups.backend.connectors;

import de.ikor.sip.adapter.processing.connectorgroups.frontend.processors.ExternalProcessor;
import de.ikor.sip.adapter.processing.scenarios.definitions.ProcessOrderScenario;
import de.ikor.sip.adapter.processing.scenarios.models.DemoModel;
import de.ikor.sip.foundation.core.declarative.annonation.OutboundConnector;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.ExecuteAfter;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.ExecuteBefore;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.ExecutionOrder;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.RequestProcessor;
import de.ikor.sip.foundation.core.declarative.annotation.rest.PathParameter;
import de.ikor.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;

@OutboundConnector(connectorGroup = "backend", integrationScenario = ProcessOrderScenario.ID, requestModel = Object.class)
public class DemoOutboundConnector extends GenericOutboundConnectorBase {
    @Override
    protected EndpointProducerBuilder defineOutgoingEndpoint() {
        return StaticEndpointBuilders.log("out");
    }

    @RequestProcessor
    @ExecutionOrder(1)
    public void processExchange(Exchange exchange) {
        DemoModel demoModel = exchange.getMessage().getBody(DemoModel.class);
        demoModel.setLatestBody(demoModel.getLatestBody() + "\nFirst request processor - processExchange: absolute order 1");
        exchange.getMessage().setBody(demoModel);
    }

    @RequestProcessor
    @ExecutionOrder(2)
    public DemoModel processModel(DemoModel demoModel) {
        demoModel.setLatestBody(demoModel.getLatestBody() + "\nThird request processor - processModel: absolute order 2");
        return demoModel;
    }

    @RequestProcessor
    @ExecuteBefore(processorName = "processModel")
    public void processMessage(Message message) {
        DemoModel demoModel = message.getBody(DemoModel.class);
        demoModel.setLatestBody(demoModel.getLatestBody() + "\nSecond request processor - processMessage: execute before processModel");
        message.setBody(demoModel);
    }

    @RequestProcessor
    @ExecuteAfter(ExternalProcessor.class)
    public void processMessageAndBody(Message message, DemoModel demoModel) {
        demoModel.setLatestBody(demoModel.getLatestBody() +
                "\nLast request processor - processMessageAndBody: execute after externalProcessor");
        message.setBody(demoModel);
    }
}
