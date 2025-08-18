package one.x1f.sip.adapter.token.scenarios.definitions;

import one.x1f.sip.adapter.token.scenarios.models.response.NobelPrizeResponse;
import one.x1f.sip.foundation.core.declarative.annotation.IntegrationScenario;
import one.x1f.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(
    scenarioId = CallSecuredServiceScenario.ID,
    requestModel = Object.class,
    responseModel = NobelPrizeResponse.class)
public class CallSecuredServiceScenario extends IntegrationScenarioBase {
  public static final String ID = "CallSecuredService";
}
