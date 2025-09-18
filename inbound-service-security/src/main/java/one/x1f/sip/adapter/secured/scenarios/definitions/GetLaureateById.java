package one.x1f.sip.adapter.secured.scenarios.definitions;

import one.x1f.sip.adapter.secured.scenarios.models.laureate.Laureate;
import one.x1f.sip.foundation.core.declarative.annotation.IntegrationScenario;
import one.x1f.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(
    scenarioId = GetLaureateById.ID,
    requestModel = Integer.class,
    responseModel = Laureate[].class)
public class GetLaureateById extends IntegrationScenarioBase {
  public static final String ID = "GetLaureateById";
}
