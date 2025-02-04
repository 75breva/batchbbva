package com.murex.processor;


import com.murex.model.catologo.CurlPlCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class UniqueCurlPlWriter implements ItemWriter<CurlPlCatalogo> {

  private static final Logger LOG = LoggerFactory.getLogger(UniqueCurlPlWriter.class);

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public UniqueCurlPlWriter(DataSource dataSource) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  public void write(List<? extends CurlPlCatalogo> items) throws Exception {
    for (CurlPlCatalogo item : items) {
      if (item != null) {
        jdbcTemplate.update("INSERT INTO cur_pl (cur_pl) VALUES (:cur_pl) ON DUPLICATE KEY UPDATE cur_pl = cur_pl",
            new BeanPropertySqlParameterSource(item));
      }
    }
    LOG.info("Writing unique CurPl information: {}", items);
  }
}