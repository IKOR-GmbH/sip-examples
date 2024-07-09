package de.ikor.sip.adapter.secured.scenarios.definitions;

import de.ikor.sip.adapter.secured.scenarios.models.NobelPrizeRequest;
import de.ikor.sip.adapter.secured.scenarios.models.nobelprize.NobelPrize;
import de.ikor.sip.foundation.core.declarative.annonation.IntegrationScenario;
import de.ikor.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(
    scenarioId = GetNobelPrizeByYearAndCategory.ID,
    requestModel = NobelPrizeRequest.class,
    responseModel = NobelPrize[].class)
public class GetNobelPrizeByYearAndCategory extends IntegrationScenarioBase {
  public static final String ID = "GetNobelPrizeByYearAndCategory";
}
