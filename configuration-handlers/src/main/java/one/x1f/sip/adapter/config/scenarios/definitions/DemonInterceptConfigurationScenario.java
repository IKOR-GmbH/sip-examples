package one.x1f.sip.adapter.config.scenarios.definitions;

import one.x1f.sip.adapter.config.config.intercept.InterceptEndpointConfiguration;
import one.x1f.sip.adapter.config.config.exception.ScenarioLevelErrorHandler;
import one.x1f.sip.foundation.core.declarative.annotation.ConfigurationHandler;
import one.x1f.sip.foundation.core.declarative.annotation.IntegrationScenario;
import one.x1f.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

/**
 * Integration Scenario used to demonstrates configuration handler with intercept on scenario level
 */
@IntegrationScenario(
    scenarioId = DemonInterceptConfigurationScenario.ID,
    requestModel = String.class,
    responseModel = String.class)
@ConfigurationHandler({ScenarioLevelErrorHandler.class, InterceptEndpointConfiguration.class})
public class DemonInterceptConfigurationScenario extends IntegrationScenarioBase {
  public static final String ID = "DemonInterceptConfigurationScenario";
}
