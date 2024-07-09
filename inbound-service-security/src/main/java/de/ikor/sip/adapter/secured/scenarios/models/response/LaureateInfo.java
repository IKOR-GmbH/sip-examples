package de.ikor.sip.adapter.secured.scenarios.models.response;

import java.util.Map;
import lombok.Data;

@Data
public class LaureateInfo {
  private String knownName;
  private String givenName;
  private String familyName;
  private String fullName;
  private String fileName;
  private String penname;
  private String gender;
  private String orgName;
  private String nativeName;
  private String acronym;
  private Map<String, String> wikipedia;
  private Map<String, String> wikidata;
}
