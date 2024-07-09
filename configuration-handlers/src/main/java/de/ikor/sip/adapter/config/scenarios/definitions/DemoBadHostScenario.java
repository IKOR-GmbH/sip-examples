package de.ikor.sip.adapter.config.scenarios.definitions;

import de.ikor.sip.adapter.config.config.intercept.InterceptEndpointConfiguration;
import de.ikor.sip.adapter.config.config.exception.ScenarioLevelErrorHandler;
import de.ikor.sip.foundation.core.declarative.annonation.ConfigurationHandler;
import de.ikor.sip.foundation.core.declarative.annonation.IntegrationScenario;
import de.ikor.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

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
