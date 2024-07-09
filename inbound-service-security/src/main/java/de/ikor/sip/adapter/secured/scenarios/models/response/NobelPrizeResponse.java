package de.ikor.sip.adapter.secured.scenarios.models.response;

import java.util.List;
import lombok.Data;

@Data
public class NobelPrizeResponse {
  private Integer awardYear;
  private String categoryFullName;
  private String dateAwarded;
  private Integer prizeAmount;
  private Integer prizeAmountAdjusted;
  private List<LaureateInfo> laureates;
}
