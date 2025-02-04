package com.murex.processor;

import com.murex.model.Ariadna;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class AriadnaProcessor implements ItemProcessor<Ariadna, Ariadna>{

    private static final Logger LOG = LoggerFactory.getLogger(MurexItemProcessor.class);

    @Override
    public Ariadna process(Ariadna item) throws Exception {
        LOG.info("Processing Ariadna information: {}", item);
        return item;
    }
}
