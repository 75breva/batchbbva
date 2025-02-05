package com.murex.processor.fenergoprocessor;

import com.murex.model.fenergo.JsonEntity;
import com.murex.model.fenergo.ledetails.LEDetails;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LetrailsProcessor implements ItemProcessor<JsonEntity, List<LEDetails>> {
    @Override
    public List<LEDetails> process(JsonEntity item) {
        return item.getLeDetails(); // Extrae solo la lista de LE_Details
    }
}
