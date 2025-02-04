package com.murex.processor;


import com.murex.model.catologo.NumoperfrontCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class NumoperfrontProcessor implements ItemProcessor<NumoperfrontCatalogo, NumoperfrontCatalogo> {

  private static final Logger LOG = LoggerFactory.getLogger(NumoperfrontProcessor.class);


  @Override
  public NumoperfrontCatalogo process(NumoperfrontCatalogo item) throws Exception {

    LOG.info("Procesando item Numoperfront: {}", item);
    return item;
  }
}
