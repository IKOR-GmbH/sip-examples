package de.ikor.sip.adapter.secured.scenarios.models.laureate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.ikor.sip.adapter.secured.scenarios.models.common.Event;
import de.ikor.sip.adapter.secured.scenarios.models.common.Location;
import de.ikor.sip.adapter.secured.scenarios.models.common.Translation;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Laureate {
  private Integer id;
  private Translation knownName;
  private Translation givenName;
  private Translation familyName;
  private Translation fullName;
  private String fileName;
  private String penname;
  private String gender;
  private Event birth;
  private Event death;
  private Translation orgName;
  private String nativeName;
  private String acronym;
  private Event founded;
  private Event dissolution;
  private Location headquarters;
  private Map<String, String> wikipedia;
  private Map<String, String> wikidata;
  private List<String> sameAs;
  private List<NobelPrizePerLaureate> nobelPrizes;
}
