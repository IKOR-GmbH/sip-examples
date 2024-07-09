package de.ikor.sip.adapter.secured.scenarios.processes;

import de.ikor.sip.adapter.secured.scenarios.definitions.GetLaureateById;
import de.ikor.sip.adapter.secured.scenarios.definitions.GetNobelPrizeAndLaureateDetails;
import de.ikor.sip.adapter.secured.scenarios.definitions.GetNobelPrizeByYearAndCategory;
import de.ikor.sip.adapter.secured.scenarios.models.NobelPrizeCommonModel;
import de.ikor.sip.adapter.secured.scenarios.models.laureate.Laureate;
import de.ikor.sip.adapter.secured.scenarios.models.nobelprize.LaureateBasic;
import de.ikor.sip.adapter.secured.scenarios.models.nobelprize.NobelPrize;
import de.ikor.sip.foundation.core.declarative.annonation.CompositeProcess;
import de.ikor.sip.foundation.core.declarative.orchestration.Orchestrator;
import de.ikor.sip.foundation.core.declarative.orchestration.process.CompositeProcessOrchestrationInfo;
import de.ikor.sip.foundation.core.declarative.orchestration.process.ProcessOrchestrator;
import de.ikor.sip.foundation.core.declarative.process.CompositeProcessBase;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Composite process which orchestrates fetching data. It will first fetch nobel prize data for a
 * certain year and category. After that it will get details for each Laureate and aggregate it into
 * the common model
 */
@CompositeProcess(
    processId = "nobel-prize-orchestrator",
    consumers = {GetNobelPrizeByYearAndCategory.class, GetLaureateById.class},
    provider = GetNobelPrizeAndLaureateDetails.class)
public class NobelPrizeProcess extends CompositeProcessBase {

  @Override
  public Orchestrator<CompositeProcessOrchestrationInfo> getOrchestrator() {
    return ProcessOrchestrator.forOrchestrationDsl(
        dsl -> {
          dsl.callConsumer(GetNobelPrizeByYearAndCategory.class)
              .withResponseHandling(
                  (latestResponse, context) -> {
                    NobelPrize[] response = (NobelPrize[]) latestResponse;
                    NobelPrizeCommonModel nobelPrizeCommonModel = new NobelPrizeCommonModel();
                    NobelPrize nobelPrize = response[0];
                    nobelPrizeCommonModel.setNobelPrize(nobelPrize);
                    // Add ids of each laureate
                    nobelPrizeCommonModel.setLaureatesIds(
                        nobelPrize.getLaureates().stream()
                            .map(LaureateBasic::getId)
                            .collect(Collectors.toList()));
                    // Set the response of the whole process.
                    // Optional.empty() is set as step result cloner
                    // since the same reference is used in this case.
                    context.setProcessResponse(nobelPrizeCommonModel, Optional.empty());
                  })
              .doWhile(
                  // While leaurate ids list is not empty, loop the consumer call
                  context ->
                      !((NobelPrizeCommonModel) context.getProcessResponse().get())
                          .getLaureatesIds()
                          .isEmpty())
              .callConsumer(GetLaureateById.class)
              .withRequestPreparation(
                  context -> {
                    NobelPrizeCommonModel nobelPrizeCommonModel =
                        (NobelPrizeCommonModel) context.getProcessResponse().get();
                    // Get an id from the laureate ids list,
                    // which will be used as a request to scenario which gets laureate details.
                    // By removing it it will make it so each iteration uses an id from the list
                    // and go through all items in list.
                    Integer id = nobelPrizeCommonModel.getLaureatesIds().remove(0);
                    return id;
                  })
              .withResponseHandling(
                  (latestResponse, context) -> {
                    // Fill the common model
                    NobelPrizeCommonModel nobelPrizeCommonModel =
                        (NobelPrizeCommonModel) context.getProcessResponse().get();
                    Laureate laureate = ((Laureate[]) latestResponse)[0];
                    nobelPrizeCommonModel.getLaureates().add(laureate);
                  })
              .endDoWhile();
        });
  }
}
