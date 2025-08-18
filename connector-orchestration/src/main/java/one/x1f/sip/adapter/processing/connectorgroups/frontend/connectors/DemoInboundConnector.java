package one.x1f.sip.adapter.processing.connectorgroups.frontend.connectors;

import one.x1f.sip.adapter.processing.scenarios.definitions.ProcessOrderScenario;
import one.x1f.sip.adapter.processing.scenarios.models.DemoModel;
import one.x1f.sip.foundation.core.declarative.annotation.InboundConnector;
import one.x1f.sip.foundation.core.declarative.annotation.connector.extension.*;
import one.x1f.sip.foundation.core.declarative.annotation.rest.ParameterMapping;
import one.x1f.sip.foundation.core.declarative.annotation.rest.PathParameter;
import one.x1f.sip.foundation.core.declarative.annotation.rest.QueryParameter;
import one.x1f.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
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
                .bindingMode(RestBindingMode.off)
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
