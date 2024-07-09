package de.ikor.sip.adapter.processing.connectorgroups.frontend.processors;

import de.ikor.sip.adapter.processing.connectorgroups.backend.connectors.DemoOutboundConnector;
import de.ikor.sip.adapter.processing.scenarios.models.DemoModel;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.ExecuteAfter;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.RequestProcessor;
import de.ikor.sip.foundation.core.declarative.connector.ConnectorProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@RequestProcessor(DemoOutboundConnector.class)
@ExecuteAfter(processorName = "processModel")
@Component
public class ExternalProcessor implements ConnectorProcessor {
    @Override
    public void process(Exchange exchange) throws Exception {
        DemoModel demoModel = exchange.getMessage().getBody(DemoModel.class);
        demoModel.setLatestBody(
                demoModel.getLatestBody() + "\nFourth request processor - externalProcessor: execute after processModel");
        exchange.getMessage().setBody(demoModel);
    }
}
