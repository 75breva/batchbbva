package com.murex.processor;

import com.murex.model.Murex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MurexItemProcessor implements ItemProcessor<Murex, Murex> {

  private static final Logger LOG = LoggerFactory.getLogger(MurexItemProcessor.class);

  @Override
  public Murex process(Murex item) throws Exception {
    // Implement any processing logic here
    // For example, you can transform or validate the item
    // In this example, we are just returning the item as is
    LOG.info("Processing Murex information: {}", item);
    return item;
  }
}