package de.ikor.sip.adapter.processing.scenarios.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DemoModel {
    private String pathParam;
    private String queryParam;
    private String originalBody;
    private String latestBody;
}
