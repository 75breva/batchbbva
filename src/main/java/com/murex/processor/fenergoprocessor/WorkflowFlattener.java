package com.murex.processor.fenergoprocessor;

import com.murex.model.fenergo.workflow.Workflow;

import java.util.List;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
/*
    Spring Batch no puede manejar List<WorkflowEntity> directamente,
    así que necesitamos un segundo ItemProcessor para "aplanar" la lista en objetos individuales.
 */
public class WorkflowFlattener implements ItemProcessor<List<Workflow>, Workflow> {
    @Override
    public Workflow process(List<Workflow> items) {
        return new IteratorItemReader<>(items).read();
    }
}
