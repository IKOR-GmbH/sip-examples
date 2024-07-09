package de.ikor.sip.adapter.token.scenarios.definitions;

import de.ikor.sip.adapter.token.scenarios.models.response.NobelPrizeResponse;
import de.ikor.sip.foundation.core.declarative.annonation.IntegrationScenario;
import de.ikor.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(
    scenarioId = CallSecuredServiceScenario.ID,
    requestModel = Object.class,
    responseModel = NobelPrizeResponse.class)
public class CallSecuredServiceScenario extends IntegrationScenarioBase {
  public static final String ID = "CallSecuredService";
}
