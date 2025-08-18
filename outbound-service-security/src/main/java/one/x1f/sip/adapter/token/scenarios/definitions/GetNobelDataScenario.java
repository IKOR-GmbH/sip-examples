package one.x1f.sip.adapter.token.scenarios.definitions;

import one.x1f.sip.adapter.token.scenarios.models.response.NobelPrizeResponse;
import one.x1f.sip.foundation.core.declarative.annotation.IntegrationScenario;
import one.x1f.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(
    scenarioId = GetNobelDataScenario.ID,
    requestModel = Object.class,
    responseModel = NobelPrizeResponse.class)
public class GetNobelDataScenario extends IntegrationScenarioBase {
  public static final String ID = "GetNobelData";
}
