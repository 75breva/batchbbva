package com.murex.processor;

import com.murex.model.catologo.CounterPartCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class UniqueCounterPartWriter extends JdbcBatchItemWriter<CounterPartCatalogo> {

  private static final Logger LOG = LoggerFactory.getLogger(UniqueCounterPartWriter.class);
  private final AtomicInteger recordsInserted = new AtomicInteger(0); // Contador de registros

  private static final Logger logger = LoggerFactory.getLogger(UniqueCounterPartWriter.class);


  private final NamedParameterJdbcTemplate jdbcTemplate;

  @Autowired
  public UniqueCounterPartWriter(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    setDataSource(dataSource);
    setSql("INSERT IGNORE INTO counterpart (value) VALUES (:value)");
    setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
  }


  @Override
  public void write(List<? extends CounterPartCatalogo> items) throws Exception {
    super.write(items);
    int insertedCount = items.size();
    recordsInserted.addAndGet(insertedCount);
    logger.info("Se han insertado {} registros en este batch. Total acumulado: {}", insertedCount, recordsInserted.get());
  }

  public int getTotalInsertedRecords() {
    return recordsInserted.get();
  }


 /* @Override
    public void write(List<? extends CounterPartCatalogo> items) throws Exception {
      int count = 0;
        for (CounterPartCatalogo item : items) {
            if (item != null) {
                jdbcTemplate.update("INSERT INTO counterpart (value) VALUES (:value) ON DUPLICATE KEY UPDATE value = value",
                        new BeanPropertySqlParameterSource(item));
              count++;
            }
        }
    LOG.info("Number of records written to the database: {}", count);
    }

  */
}
