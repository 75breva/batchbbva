package com.murex.model.fenergo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.murex.model.fenergo.docmatrix.DocumentMatrix;
import com.murex.model.fenergo.ledetails.LEDetails;
import com.murex.model.fenergo.workflow.Workflow;
import lombok.Data;
import java.util.List;

@Data
public class JsonEntity {
    @JsonProperty("LE_Details")
    private List<LEDetails> leDetails;
    @JsonProperty("Document_Matrix")
    private List<DocumentMatrix> documentMatrix;
    @JsonProperty("Workflow")
    private List<Workflow> workflow;
}

