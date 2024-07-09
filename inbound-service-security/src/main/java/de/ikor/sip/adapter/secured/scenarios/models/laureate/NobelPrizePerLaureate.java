package de.ikor.sip.adapter.secured.scenarios.models.laureate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.ikor.sip.adapter.secured.scenarios.models.common.Entity;
import de.ikor.sip.adapter.secured.scenarios.models.common.Residence;
import de.ikor.sip.adapter.secured.scenarios.models.common.Translation;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NobelPrizePerLaureate {
  private Integer awardYear;
  private Translation category;
  private Translation categoryFullName;
  private String sortOrder;
  private String portion;
  private String dateAwarded;
  private String prizeStatus;
  private Translation motivation;
  private Integer prizeAmount;
  private Integer prizeAmountAdjusted;
  private List<Entity> affiliations;
  private List<Residence> residences;
}
