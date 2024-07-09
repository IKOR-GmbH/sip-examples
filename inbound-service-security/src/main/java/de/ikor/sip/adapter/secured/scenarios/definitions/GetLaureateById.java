package de.ikor.sip.adapter.secured.scenarios.definitions;

import de.ikor.sip.adapter.secured.scenarios.models.laureate.Laureate;
import de.ikor.sip.foundation.core.declarative.annonation.IntegrationScenario;
import de.ikor.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(
    scenarioId = GetLaureateById.ID,
    requestModel = Integer.class,
    responseModel = Laureate[].class)
public class GetLaureateById extends IntegrationScenarioBase {
  public static final String ID = "GetLaureateById";
}
