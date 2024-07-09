package de.ikor.sip.adapter.token.connectorgroups.frontend.connectors;

import de.ikor.sip.adapter.token.connectorgroups.frontend.models.NobelPrizeCategory;
import de.ikor.sip.adapter.token.scenarios.definitions.GetNobelDataScenario;
import de.ikor.sip.adapter.token.scenarios.models.response.NobelPrizeResponse;
import de.ikor.sip.foundation.core.declarative.annonation.InboundConnector;
import de.ikor.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
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
