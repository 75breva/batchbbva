package com.murex.model.fenergo.ledetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BusinessDetails {
    @JsonProperty("BBVA_Institution_Code")
    private String bbvaInstitutionCode;

    @JsonProperty("Is_this_entity_publicly_listed")
    private String isThisEntityPubliclyListed;

    @JsonProperty("Is_this_entity_regulated")
    private String isThisEntityRegulated;
}
