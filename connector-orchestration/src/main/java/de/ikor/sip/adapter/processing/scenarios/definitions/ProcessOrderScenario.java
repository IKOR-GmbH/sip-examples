package de.ikor.sip.adapter.processing.scenarios.definitions;

import de.ikor.sip.foundation.core.declarative.annonation.IntegrationScenario;
import de.ikor.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(scenarioId = ProcessOrderScenario.ID, requestModel = Object.class)
public class ProcessOrderScenario extends IntegrationScenarioBase {
    public static final String ID = "ProcessOrderScenario";
}
