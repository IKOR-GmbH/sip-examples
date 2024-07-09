package de.ikor.sip.adapter.token.scenarios.processes;

import de.ikor.sip.adapter.token.scenarios.definitions.CallSecuredServiceScenario;
import de.ikor.sip.adapter.token.scenarios.definitions.FetchTokenScenario;
import de.ikor.sip.adapter.token.scenarios.definitions.GetNobelDataScenario;
import de.ikor.sip.foundation.core.declarative.annonation.CompositeProcess;
import de.ikor.sip.foundation.core.declarative.orchestration.Orchestrator;
import de.ikor.sip.foundation.core.declarative.orchestration.process.CompositeProcessOrchestrationInfo;
import de.ikor.sip.foundation.core.declarative.orchestration.process.ProcessOrchestrator;
import de.ikor.sip.foundation.core.declarative.process.CompositeProcessBase;

/**
 * Composite process which orchestrates fetching data. It will first fetch the token from keycloak
 * server. After that it will fetch The Nobel Prize details from secured demo adapter
 */
@CompositeProcess(
    processId = "fetch-token-orchestrator",
    consumers = {CallSecuredServiceScenario.class, FetchTokenScenario.class},
    provider = GetNobelDataScenario.class)
public class FetchTokenProcess extends CompositeProcessBase {

  @Override
  public Orchestrator<CompositeProcessOrchestrationInfo> getOrchestrator() {
    return ProcessOrchestrator.forOrchestrationDsl(
        dsl -> {
          dsl.callConsumer(FetchTokenScenario.class)
              .withNoResponseHandling()
              .callConsumer(CallSecuredServiceScenario.class)
              .withNoResponseHandling();
        });
  }
}
