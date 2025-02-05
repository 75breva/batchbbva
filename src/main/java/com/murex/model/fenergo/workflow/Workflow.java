package com.murex.model.fenergo.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "workflow")
@Data
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "case_type")
    @JsonProperty("Case_Type")
    private String caseType;

    @Column(name = "case_type_status")
    @JsonProperty("Case_Type_Status")
    private String caseTypeStatus;

    @Column(name = "case_created_date")
    @JsonProperty("Case_Created_Date")
    private String caseCreatedDate;
}
