package de.ikor.sip.adapter.secured.scenarios.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
  private Translation city;
  private Translation country;
  private Translation cityNow;
  private Translation countryNow;
  private Translation continent;
  private Translation locationString;
}
