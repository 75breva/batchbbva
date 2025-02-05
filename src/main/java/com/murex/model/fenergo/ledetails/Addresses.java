package com.murex.model.fenergo.ledetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Addresses {
    @JsonProperty("Addres_Line_1")
    private String addressLine1;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Postal_Code_ZIP")
    private String postalCodeZip;

    @JsonProperty("Address_Type")
    private String addressType;

    @JsonProperty("Town_City")
    private String townCity;

    @JsonProperty("State_County")
    private String stateCounty;
}
