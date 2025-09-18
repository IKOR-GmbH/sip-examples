package one.x1f.sip.adapter.secured.scenarios.definitions;

import one.x1f.sip.adapter.secured.scenarios.models.NobelPrizeRequest;
import one.x1f.sip.adapter.secured.scenarios.models.nobelprize.NobelPrize;
import one.x1f.sip.foundation.core.declarative.annotation.IntegrationScenario;
import one.x1f.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(
    scenarioId = GetNobelPrizeByYearAndCategory.ID,
    requestModel = NobelPrizeRequest.class,
    responseModel = NobelPrize[].class)
public class GetNobelPrizeByYearAndCategory extends IntegrationScenarioBase {
  public static final String ID = "GetNobelPrizeByYearAndCategory";
}
