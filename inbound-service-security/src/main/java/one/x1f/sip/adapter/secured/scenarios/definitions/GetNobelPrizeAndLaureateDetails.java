package one.x1f.sip.adapter.secured.scenarios.definitions;

import one.x1f.sip.adapter.secured.scenarios.models.NobelPrizeCommonModel;
import one.x1f.sip.adapter.secured.scenarios.models.NobelPrizeRequest;
import one.x1f.sip.foundation.core.declarative.annotation.IntegrationScenario;
import one.x1f.sip.foundation.core.declarative.scenario.IntegrationScenarioBase;

@IntegrationScenario(
    scenarioId = GetNobelPrizeAndLaureateDetails.ID,
    requestModel = NobelPrizeRequest.class,
    responseModel = NobelPrizeCommonModel.class)
public class GetNobelPrizeAndLaureateDetails extends IntegrationScenarioBase {
  public static final String ID = "GetNobelPrizeAndLaureateDetails";
}
