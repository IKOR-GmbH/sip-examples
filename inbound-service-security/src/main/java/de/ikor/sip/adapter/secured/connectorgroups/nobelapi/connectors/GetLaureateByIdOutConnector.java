package de.ikor.sip.adapter.secured.connectorgroups.nobelapi.connectors;

import de.ikor.sip.adapter.secured.scenarios.definitions.GetLaureateById;
import de.ikor.sip.adapter.secured.scenarios.models.laureate.Laureate;
import de.ikor.sip.foundation.core.declarative.annonation.OutboundConnector;
import de.ikor.sip.foundation.core.declarative.annotation.connector.processor.RequestProcessor;
import de.ikor.sip.foundation.core.declarative.connector.GenericOutboundConnectorBase;
import de.ikor.sip.foundation.core.declarative.model.UnmarshallerDefinition;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;
import org.apache.camel.component.jackson.JacksonDataFormat;

/**
 * Outbound connector which calls the public Nobel Prize API: GET
 * https://api.nobelprize.org/2.0/laureate/{id} It fetches the data of a Laureate by their id.
 */
@OutboundConnector(
    connectorGroup = "nobelprize",
    integrationScenario = GetLaureateById.ID,
    requestModel = Integer.class,
    responseModel = Laureate[].class,
    connectorId = "GetLaureateByIdOutConnector")
public class GetLaureateByIdOutConnector extends GenericOutboundConnectorBase {

  // Define external outbound endpoint
  @Override
  protected EndpointProducerBuilder defineOutgoingEndpoint() {
    return StaticEndpointBuilders.http("https", "api.nobelprize.org/2.0/laureate/${body}")
        .bridgeEndpoint(true);
  }

  // Define request transformation
  @RequestProcessor
  public void setRequest(Exchange exchange) {
        // Required by Nobel Prize API, otherwise the response is compressed and cannot be
        // processed
        exchange.getMessage().setHeader("Accept-Encoding", "deflate");
  }

  // Define unmarshalling method of response
  @Override
  protected Optional<UnmarshallerDefinition> defineResponseUnmarshalling() {
    return Optional.of(
        UnmarshallerDefinition.forDataFormat(new JacksonDataFormat(Laureate[].class)));
  }
}
