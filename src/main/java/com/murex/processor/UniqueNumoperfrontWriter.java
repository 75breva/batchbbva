package com.murex.processor;

import com.murex.model.catologo.NumoperfrontCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UniqueNumoperfrontWriter implements ItemWriter<NumoperfrontCatalogo> {

  private static final Logger LOG = LoggerFactory.getLogger(UniqueNumoperfrontWriter.class);

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public UniqueNumoperfrontWriter(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void write(List<? extends NumoperfrontCatalogo> items) throws Exception {
    int count = 0;
    for (NumoperfrontCatalogo item : items) {
      if (item != null) {
        jdbcTemplate.update("INSERT INTO numoperfront (value) VALUES (:value) ON DUPLICATE KEY UPDATE value = value",
            new BeanPropertySqlParameterSource(item));
        count++;
      }
    }
    LOG.info("Number of records written to the database: {}", count);
  }
}



//@Component
//public class UniqueFecProcesoWriter extends JdbcBatchItemWriter<FecProcesoCatalogo> {
//
//  private static final Logger LOG = LoggerFactory.getLogger(UniqueFecProcesoWriter.class);
//  private final AtomicInteger recordsInserted = new AtomicInteger(0); // Contador de registros
//
//  private static final Logger logger = LoggerFactory.getLogger(UniqueFecProcesoWriter.class);
//
//
//  private final NamedParameterJdbcTemplate jdbcTemplate;
//
//  public UniqueFecProcesoWriter(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
//    this.jdbcTemplate = jdbcTemplate;
//    setDataSource(dataSource);
//    setSql("INSERT IGNORE INTO fecproceso (fec_proceso) VALUES (:fec_proceso)");
//    setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//  }
//
//
//  @Override
//  public void write(List<? extends FecProcesoCatalogo> items) throws Exception {
//    super.write(items);
//    int insertedCount = items.size();
//    recordsInserted.addAndGet(insertedCount);
//    logger.info("Se han insertado {} registros en este batch. Total acumulado: {}", insertedCount, recordsInserted.get());
//  }
//
//  public int getTotalInsertedRecords() {
//    return recordsInserted.get();
//  }
