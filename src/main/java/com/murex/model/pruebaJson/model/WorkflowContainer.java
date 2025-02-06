package com.murex.model.pruebaJson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor // Constructor por defecto
@AllArgsConstructor // Constructor con todos los argumentos
public class WorkflowContainer {
    @JsonProperty("WorkflowMy")
    private List<WorkflowMy> workflowMyList;
}
