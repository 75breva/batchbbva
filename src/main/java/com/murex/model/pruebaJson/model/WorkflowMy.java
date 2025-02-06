package com.murex.model.pruebaJson.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workflow")
public class WorkflowMy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("isin")
    @Column(name = "isin")
    private String isin;
    @JsonProperty("quantity")
    @Column(name = "quantity")
    private Integer quantity;
    @JsonProperty("customer")
    @Column(name = "customer")
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
