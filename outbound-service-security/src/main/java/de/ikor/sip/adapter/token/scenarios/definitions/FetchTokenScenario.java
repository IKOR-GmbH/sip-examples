package de.ikor.sip.adapter.token.scenarios.definitions;

import de.ikor.sip.foundation.core.declarative.annonation.IntegrationScenario;
import de.ikor.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(scenarioId = FetchTokenScenario.ID, requestModel = Object.class)
public class FetchTokenScenario extends IntegrationScenarioBase {
  public static final String ID = "FetchToken";
}
