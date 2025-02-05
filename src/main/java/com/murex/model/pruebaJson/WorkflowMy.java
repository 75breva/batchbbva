package com.murex.model.pruebaJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.murex.model.fenergo.workflow.Workflow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor // Constructor por defecto
@AllArgsConstructor // Constructor con todos los argumentos
public class WorkflowMy {
    @JsonProperty("WorkflowMy")
    private List<MyObject> workflow;
}
