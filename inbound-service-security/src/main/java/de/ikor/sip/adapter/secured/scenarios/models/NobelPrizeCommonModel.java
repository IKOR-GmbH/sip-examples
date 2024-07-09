package de.ikor.sip.adapter.secured.scenarios.models;

import de.ikor.sip.adapter.secured.scenarios.models.laureate.Laureate;
import de.ikor.sip.adapter.secured.scenarios.models.nobelprize.NobelPrize;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class NobelPrizeCommonModel {
  private NobelPrize nobelPrize;
  private List<Laureate> laureates = new ArrayList<>();
  private List<Integer> laureatesIds;
}
