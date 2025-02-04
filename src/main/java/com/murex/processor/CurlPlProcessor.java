package com.murex.processor;

import com.murex.model.catologo.CurlPlCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CurlPlProcessor implements ItemProcessor<CurlPlCatalogo, CurlPlCatalogo> {

  private static final Logger LOG = LoggerFactory.getLogger(CurlPlProcessor.class);

  private Set<String> uniqueValues = new HashSet<>();

  @Override
  public CurlPlCatalogo process(CurlPlCatalogo item) throws Exception {

    if (uniqueValues.add(item.getCur_pl())) {
      return item;
    } else {
      return null; // Return null to filter out duplicates
    }

  }
}
