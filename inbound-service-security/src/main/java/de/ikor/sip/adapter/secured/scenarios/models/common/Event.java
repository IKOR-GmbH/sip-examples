package de.ikor.sip.adapter.secured.scenarios.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
  private String date;
  private Location place;
}
