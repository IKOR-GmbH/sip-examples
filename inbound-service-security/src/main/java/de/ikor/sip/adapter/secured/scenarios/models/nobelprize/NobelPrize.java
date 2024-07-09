package de.ikor.sip.adapter.secured.scenarios.models.nobelprize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.ikor.sip.adapter.secured.scenarios.models.common.Translation;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NobelPrize {
  private Integer awardYear;
  private Translation category;
  private Translation categoryFullName;
  private String dateAwarded;
  private Integer prizeAmount;
  private Integer prizeAmountAdjusted;
  private Translation topMotivation;
  private List<LaureateBasic> laureates = new ArrayList<>();
}
