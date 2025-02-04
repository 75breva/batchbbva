package com.murex.processor;

import com.murex.model.catologo.CounterPartCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CounterPartProcessor implements ItemProcessor<CounterPartCatalogo, CounterPartCatalogo> {

  private static final Logger LOG = LoggerFactory.getLogger(CounterPartProcessor.class);

  private final NamedParameterJdbcTemplate jdbcTemplate;

  @Autowired
  public CounterPartProcessor(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private Set<String> uniqueValues = new HashSet<>();

    @Override
    public CounterPartCatalogo process(CounterPartCatalogo item) throws Exception {

      if(uniqueValues.add(item.getValue())) {
        return item;
      } else {
        return null;
      }
    }
}
