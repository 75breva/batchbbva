package com.murex.processor.fenergoprocessor;

import com.murex.model.fenergo.JsonEntity;
import com.murex.processor.MurexItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FenergoProcessor  implements ItemProcessor<JsonEntity,JsonEntity> {
    private static final Logger LOG = LoggerFactory.getLogger(MurexItemProcessor.class);
    private Set<String> uniqueValues = new HashSet<>();
    @Override
    public JsonEntity process(JsonEntity item) {
        // Validación básica
        if (item == null) {
            System.out.println(" JsonEntity nulo, descartando...");
            return null; // Esto hace que se ignore el registro.
        }

        System.out.println("🔄 Procesando entidad JSON...");

        // Filtrar detalles vacíos si es necesario
        if (item.getLeDetails() == null || item.getLeDetails().isEmpty()) {
            System.out.println("⚠️ LE_Details está vacío.");
        }

        if (item.getDocumentMatrix() == null || item.getDocumentMatrix().isEmpty()) {
            System.out.println("⚠️ Document_Matrix está vacío.");
        }

        if (item.getWorkflow() == null || item.getWorkflow().isEmpty()) {
            System.out.println("⚠️ Workflow está vacío.");
        }

        return item; // Se devuelve el objeto sin cambios si pasa las validaciones.
    }
}
