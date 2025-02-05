package com.murex.model.pruebaJson;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyObject{
    @JsonProperty("isin")
    private String iSin;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("customer")
    private String customer;
}

/*
@Data
public class MyObject {
    private String iSin;
    private Integer quantity;
    private String customer;

    @JsonCreator
    public MyObject(@JsonProperty("isin") String iSin,
                    @JsonProperty("quantity") Integer quantity,
                    @JsonProperty("customer") String customer) {
        this.iSin = iSin;
        this.quantity = quantity;
        this.customer = customer;
    }
}
*/
