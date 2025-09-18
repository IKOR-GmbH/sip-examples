package one.x1f.sip.adapter.processing.scenarios.definitions;

import one.x1f.sip.foundation.core.declarative.annotation.IntegrationScenario;
import one.x1f.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(scenarioId = ProcessOrderScenario.ID, requestModel = Object.class)
public class ProcessOrderScenario extends IntegrationScenarioBase {
    public static final String ID = "ProcessOrderScenario";
}
