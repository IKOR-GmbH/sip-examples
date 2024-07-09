package de.ikor.sip.adapter.secured.connectorgroups.nobelapi.connectors;

import static org.apache.camel.Exchange.HTTP_PATH;
import static org.apache.camel.builder.Builder.constant;

import de.ikor.sip.adapter.secured.scenarios.definitions.GetNobelPrizeByYearAndCategory;
import de.ikor.sip.adapter.secured.scenarios.models.NobelPrizeRequest;
import de.ikor.sip.adapter.secured.scenarios.models.nobelprize.NobelPrize;
import de.ikor.sip.foundation.core.declarative.annonation.OutboundConnector;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.RequestProcessor;
import de.ikor.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
import de.ikor.sip.foundation.core.declarative.model.UnmarshallerDefinition;
import de.ikor.sip.foundation.core.declarative.orchestration.Orchestrator;
import de.ikor.sip.foundation.core.declarative.orchestration.connector.ConnectorOrchestrationInfo;
import de.ikor.sip.foundation.core.declarative.orchestration.connector.ConnectorOrchestrator;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;
import org.apache.camel.model.RouteDefinition;

/**
 * Outbound connector which calls the public Nobel Prize API: GET
 * https://api.nobelprize.org/2.0/nobelPrize/{category}/{year} It fetches the data of a Nobel Prize
 * by the category and year.
 */
@OutboundConnector(
    connectorGroup = "nobelprize",
    integrationScenario = GetNobelPrizeByYearAndCategory.ID,
    requestModel = NobelPrizeRequest.class,
    responseModel = NobelPrize[].class,
    connectorId = "GetNobelPrizeByYearAndCategoryOutConnector")
public class GetNobelPrizeByYearAndCategoryOutConnector extends GenericOutboundConnectorBase {
  // Define external endpoint
  @Override
  protected EndpointProducerBuilder defineOutgoingEndpoint() {
    return StaticEndpointBuilders.http(
            "https", "api.nobelprize.org/2.0/nobelPrize/${body.category}/${body.year}")
        .bridgeEndpoint(true);
  }

  @RequestProcessor
  public void setRequest(Exchange exchange) {
    // Due to Apache Camel restrictions the old http path should be removed,
    // otherwise it will be appended in the next request
    exchange.getMessage().removeHeader(HTTP_PATH);
    // Required by Nobel Prize API, otherwise the response is compressed and cannot be
    // processed
    exchange.getMessage().setHeader("Accept-Encoding", "deflate");
    exchange.getMessage().setHeader("Accept-Charset", "UTF-8");
  }

  @Override
  protected Optional<UnmarshallerDefinition> defineResponseUnmarshalling() {
    return Optional.of(
        UnmarshallerDefinition.forClause(unmarshaller -> unmarshaller.json(NobelPrize[].class)));
  }
}
