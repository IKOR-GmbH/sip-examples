package de.ikor.sip.adapter.secured.connectorgroups.frontend.connectors;

import de.ikor.sip.adapter.secured.config.NobelPrizeMapper;
import de.ikor.sip.adapter.secured.connectorgroups.frontend.models.NobelPrizeCategory;
import de.ikor.sip.adapter.secured.scenarios.definitions.GetNobelPrizeAndLaureateDetails;
import de.ikor.sip.adapter.secured.scenarios.models.NobelPrizeCommonModel;
import de.ikor.sip.adapter.secured.scenarios.models.NobelPrizeRequest;
import de.ikor.sip.adapter.secured.scenarios.models.response.NobelPrizeResponse;
import de.ikor.sip.foundation.core.declarative.annonation.InboundConnector;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.RequestProcessor;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.ResponseProcessor;
import de.ikor.sip.foundation.core.declarative.connector.RestInboundConnectorBase;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.model.rest.RestDefinition;
import org.apache.camel.model.rest.RestParamType;

/**
 * Inbound connector which exposes GET /adapter/nobelprize/{category}/{year} endpoint. Used to fetch
 * details about a Nobel Prize and its Laureates for a certain category and year.
 */
@InboundConnector(
    connectorId = "GetNobelPrizeDetailsInConnector",
    connectorGroup = "fe",
    requestModel = String.class,
    responseModel = NobelPrizeCommonModel.class,
    integrationScenario = GetNobelPrizeAndLaureateDetails.ID)
@RequiredArgsConstructor
public class GetNobelPrizeDetailsInConnector extends RestInboundConnectorBase {

  private static final String BEARER_AUTH = "bearerAuth";
  private final NobelPrizeMapper nobelPrizeMapper;

  // Define REST endpoint
  @Override
  protected void configureRest(RestDefinition restDefinition) {
    restDefinition
        .get("/nobelprize/{category}/{year}")
        .securityDefinitions() // this needs to be defined only on one endpoint
        .bearerToken(BEARER_AUTH, "Bearer Token (Keycloak)", "JWT")
        .end()
        .security(BEARER_AUTH)
        .tag("Nobel Prize")
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

  // Define request transformation
  @RequestProcessor
  public void setRequest(Exchange exchange) {
      NobelPrizeCategory category =
          exchange.getMessage().getHeader("category", NobelPrizeCategory.class);
      String year = exchange.getMessage().getHeader("year", String.class);
      NobelPrizeRequest nobelPrizeRequest =
          NobelPrizeRequest.builder().category(category.getValue()).year(year).build();
      exchange.getMessage().setBody(nobelPrizeRequest);
  }

  // Define response transformation
  @ResponseProcessor
  public void setResponse(Exchange exchange) {
      NobelPrizeCommonModel nobelPrizeCommonModel =
          exchange.getMessage().getBody(NobelPrizeCommonModel.class);
      NobelPrizeResponse nobelPrizeResponse =
          nobelPrizeMapper.toNobelPrizeResponse(nobelPrizeCommonModel.getNobelPrize());
      nobelPrizeResponse.setLaureates(
          nobelPrizeMapper.toLaureates(nobelPrizeCommonModel.getLaureates()));
      exchange.getMessage().setBody(nobelPrizeResponse);
  }

  private String getNobelPrizeCategoryValues() {
    StringJoiner joiner = new StringJoiner(",");
    for (NobelPrizeCategory category : NobelPrizeCategory.values()) {
      joiner.add(category.toString().toLowerCase());
    }
    return joiner.toString();
  }
}
