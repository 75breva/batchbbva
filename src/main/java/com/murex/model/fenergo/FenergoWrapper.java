package com.murex.model.fenergo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class FenergoWrapper {
    @JsonProperty("Fenergo")
    private Map<String, JsonEntity> fenergoEntities;
}
