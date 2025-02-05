package com.murex.model.fenergo.ledetails;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class LEDetails {
    @JsonProperty("BUSINESS_DETAILS")
    private List<BusinessDetails> businessDetails;
    @JsonProperty("ADDRESSES")
    private List<Addresses> ADDRESSES;
}

