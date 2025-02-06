package com.murex.model.pruebaJson;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class ProcessorConfig {

    @Bean
    @Profile("defaultProcessor")
    public ItemProcessor<WorkflowContainer, WorkflowContainer> jsonItemProcessor() {
        return user -> {
            // Process user if needed
            return user;
        };
    }

    @Bean
    @Profile("listProcessor")
    public ItemProcessor<WorkflowContainer, List<WorkflowMy>> jsonItemProcessorListWorkflowMy() {
        return user -> {
            // Process user if needed
            return user.getWorkflowMyList();
        };
    }
}
