package com.murex.model.pruebaJson;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ReaderConfig {

    @Bean
    @StepScope
    @Profile("defaultReader")
    public JsonItemReader<WorkflowContainer> jsonItemReaderPrueba() {
        return new JsonItemReaderBuilder<WorkflowContainer>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(WorkflowContainer.class))
                .resource(new ClassPathResource("prueba2_1.json"))
                .name("WorkflowMyJsonItemReader")
                .build();
    }
}
