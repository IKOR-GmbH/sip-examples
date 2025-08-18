package one.x1f.sip.adapter.secured.scenarios.models;

import one.x1f.sip.adapter.secured.scenarios.models.laureate.Laureate;
import one.x1f.sip.adapter.secured.scenarios.models.nobelprize.NobelPrize;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class NobelPrizeCommonModel {
  private NobelPrize nobelPrize;
  private List<Laureate> laureates = new ArrayList<>();
  private List<Integer> laureatesIds;
}
