package one.x1f.sip.adapter.token.scenarios.definitions;

import one.x1f.sip.foundation.core.declarative.annotation.IntegrationScenario;
import one.x1f.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(scenarioId = FetchTokenScenario.ID, requestModel = Object.class)
public class FetchTokenScenario extends IntegrationScenarioBase {
  public static final String ID = "FetchToken";
}
