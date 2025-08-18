package one.x1f.sip.adapter.secured.scenarios.models.nobelprize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import one.x1f.sip.adapter.secured.scenarios.models.common.Translation;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LaureateBasic {
  private Integer id;
  private Translation name;
  private Translation knownName;
  private Translation fullName;
  private String portion;
  private String sortOrder;
  private Translation motivation;
}
