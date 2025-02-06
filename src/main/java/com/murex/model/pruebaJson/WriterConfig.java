package com.murex.model.pruebaJson;

import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@Configuration
public class WriterConfig {

    @Bean
    @Profile("defaultWriter")
    public JsonFileItemWriter<WorkflowContainer> jsonItemWriter() {
        return new JsonFileItemWriterBuilder<WorkflowContainer>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("output2_1.json"))
                .name("jsonItemWriter")
                .build();
    }

    @Bean
    @Profile("listWriter")
    public JsonFileItemWriter<List<WorkflowMy>> jsonItemWriterListWorkFlowMy() {
        return new JsonFileItemWriterBuilder<List<WorkflowMy>>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("output2_1.json"))
                .name("jsonItemWriter")
                .build();
    }

    @Bean
    @Profile("singleWriter")
    public JsonFileItemWriter<WorkflowMy> jsonItemWriter2() {
        return new JsonFileItemWriterBuilder<WorkflowMy>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("output2_2.json"))
                .name("jsonItemWriter")
                .build();
    }
}
