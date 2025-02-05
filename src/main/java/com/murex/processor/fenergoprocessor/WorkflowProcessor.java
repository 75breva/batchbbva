package com.murex.processor.fenergoprocessor;
import java.util.List;

import org.slf4j.Logger;
import com.murex.model.fenergo.JsonEntity;

import com.murex.model.fenergo.workflow.Workflow;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class WorkflowProcessor implements ItemProcessor<JsonEntity, List<Workflow>> {
    private static final Logger LOG = LoggerFactory.getLogger(WorkflowProcessor.class);

    @Override
    public List<Workflow> process(JsonEntity item) {
        try {
            LOG.info("Procesando item Workflow: {}", item);
            return item.getWorkflow(); // Extrae solo la lista de Workflow
        } catch (Exception e) {
            LOG.error("Error procesando item Workflow: {}", item, e);
            throw e; // Re-throw the exception to ensure it gets handled by the batch framework
        }
    }
}

/*  // Parte  compleja "Fenergo": { "1","41" }
@Component
public class WorkflowProcessor implements ItemProcessor<FenergoWrapper, List<Workflow>> {

    @Override
    public List<Workflow> process(FenergoWrapper wrapper) {
        List<Workflow> workflows = new ArrayList<>();

        if (wrapper.getFenergoEntities() == null) return Collections.emptyList();

        for (Map.Entry<String, JsonEntity> entry : wrapper.getFenergoEntities().entrySet()) {
            JsonEntity entity = entry.getValue();

            if (entity.getWorkflow() != null) {
                for (Workflow workflow : entity.getWorkflow()) {
                    Workflow workflowEntity = new Workflow();
                    workflowEntity.setCase_Type(workflow.getCase_Type());
                    workflowEntity.setCase_Type_Status(workflow.getCase_Type_Status());
                    workflowEntity.setCase_Created_Date(workflow.getCase_Created_Date());
                    //workflowEntity.setId(entry.get()); // Guardamos la ID del cliente

                    workflows.add(workflowEntity);
                }
            }
        }
        return workflows;
    }
}*/
