package one.x1f.sip.adapter.token.connectorgroups.frontend.connectors;

import one.x1f.sip.adapter.token.connectorgroups.frontend.models.NobelPrizeCategory;
import one.x1f.sip.adapter.token.scenarios.definitions.GetNobelDataScenario;
import one.x1f.sip.adapter.token.scenarios.models.response.NobelPrizeResponse;
import one.x1f.sip.foundation.core.declarative.annotation.InboundConnector;
import one.x1f.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import java.util.StringJoiner;
import org.apache.camel.model.rest.RestDefinition;
import org.apache.camel.model.rest.RestParamType;

/** Inbound connector to trigger process */
@InboundConnector(
    connectorId = "SendRequestInConnector",
    connectorGroup = "frontend",
    requestModel = String.class,
    responseModel = NobelPrizeResponse.class,
    integrationScenario = GetNobelDataScenario.ID)
public class SendRequestInConnector extends RestInboundConnectorBase {

  // Define REST endpoint
  @Override
  protected void configureRest(RestDefinition restDefinition) {
    restDefinition
        .get("/nobelprize/{category}/{year}")
        .outType(NobelPrizeResponse.class)
        .param()
        .name("category")
        .allowableValues(getNobelPrizeCategoryValues())
        .type(RestParamType.path)
        .endParam()
        .param()
        .name("year")
        .type(RestParamType.path)
        .endParam();
  }

  private String getNobelPrizeCategoryValues() {
    StringJoiner joiner = new StringJoiner(",");
    for (NobelPrizeCategory category : NobelPrizeCategory.values()) {
      joiner.add(category.toString().toLowerCase());
    }
    return joiner.toString();
  }
}
