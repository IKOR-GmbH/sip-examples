package one.x1f.sip.adapter.config.scenarios.definitions;

import one.x1f.sip.adapter.config.config.intercept.InterceptEndpointConfiguration;
import one.x1f.sip.adapter.config.config.exception.ScenarioLevelErrorHandler;
import one.x1f.sip.foundation.core.declarative.annotation.ConfigurationHandler;
import one.x1f.sip.foundation.core.declarative.annotation.IntegrationScenario;
import one.x1f.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

/**
 * Integration Scenario used to demonstrate configuration handler with exception handling on scenario level
 */
@IntegrationScenario(
    scenarioId = DemoBadHostScenario.ID,
    requestModel = String.class,
    responseModel = String.class)
@ConfigurationHandler({ScenarioLevelErrorHandler.class, InterceptEndpointConfiguration.class})
public class DemoBadHostScenario extends IntegrationScenarioBase {
  public static final String ID = "DemoBadHostScenario";
}
