package de.ikor.sip.adapter.processing.connectorgroups.frontend.connectors;

import de.ikor.sip.adapter.processing.connectorgroups.frontend.processors.ExternalProcessor;
import de.ikor.sip.adapter.processing.scenarios.definitions.ProcessOrderScenario;
import de.ikor.sip.adapter.processing.scenarios.models.DemoModel;
import de.ikor.sip.foundation.core.declarative.annonation.InboundConnector;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.*;
import de.ikor.sip.foundation.core.declarative.annotation.rest.ParameterMapping;
import de.ikor.sip.foundation.core.declarative.annotation.rest.PathParameter;
import de.ikor.sip.foundation.core.declarative.annotation.rest.QueryParameter;
import de.ikor.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestDefinition;
import org.apache.camel.model.rest.RestParamType;

@InboundConnector(connectorGroup = "frontend",
        integrationScenario = ProcessOrderScenario.ID,
        requestModel = String.class)
public class DemoInboundConnector extends RestInboundConnectorBase {
    @Override
    protected void configureRest(RestDefinition restDefinition) {
        restDefinition
                .post("/demo/{path}")
                .bindingMode(RestBindingMode.auto)
                .consumes("text/plain")
                .type(String.class)
                .param()
                    .name("path")
                    .type(RestParamType.path)
                .endParam()
                .param()
                    .name("query")
                    .type(RestParamType.query)
                    .required(true)
                .endParam();
    }

    @ParameterMapping
    public void mapQueryParameters(
            Message message,
            @PathParameter("path") String path,
            @QueryParameter("query") String query) {
        String body = message.getBody(String.class);
        DemoModel mappedData =
                DemoModel.builder()
                        .pathParam(path)
                        .queryParam(query)
                        .originalBody(body)
                        .latestBody(body)
                        .build();
        message.setBody(mappedData);
    }

    @ResponseProcessor
    public String processResponse(DemoModel demoModel,
                                  @PathParameter("path") String path,
                                  @QueryParameter("query") String query) {
        return demoModel.getLatestBody()
                + "\nFinal response processor: "
                + "\nappend path param: " + path
                + "\nappend query param: " + query;
    }
}
