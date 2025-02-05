package com.murex.processor.fenergoprocessor;

import com.murex.model.fenergo.JsonEntity;
import com.murex.model.fenergo.docmatrix.DocumentMatrix;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DocumentMatrixProcessor implements ItemProcessor <JsonEntity, List<DocumentMatrix>> {

    @Override
    public List<DocumentMatrix> process(JsonEntity item) {
        return item.getDocumentMatrix(); // Extrae solo la lista de DocumentMatrix
    }
}
