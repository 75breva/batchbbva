package com.murex.model.fenergo.docmatrix;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class DocumentMatrix {
    @JsonProperty("Document_Name")
    private String Document_Name;

    @JsonProperty("Created_Date")
    private String Created_Date;

    @JsonProperty("Tax_Form_Signed_Date")
    private String Tax_Form_Signed_Date;

    @JsonProperty("Expiration_Date")
    private String Expiration_Date;

    @JsonProperty("Document_Category")
    private String Document_Category;

    @JsonProperty("Document_Type")
    private String Document_Type;

    @JsonProperty("Document_Status")
    private String Document_Status;
}
