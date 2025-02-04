package com.murex.processor;

import com.murex.model.catologo.NumoperfrontCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.murex.model.Ariadna;
import org.springframework.batch.item.ItemWriter;

import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UniqueAriadnaWriter extends JdbcBatchItemWriter<Ariadna> {

    private static final Logger logger = LoggerFactory.getLogger(UniqueAriadnaWriter.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AtomicInteger recordsInserted = new AtomicInteger(0); // Contador de registros

    public UniqueAriadnaWriter(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        setDataSource(dataSource);
        setSql("INSERT IGNORE INTO fecproceso (fec_proceso) VALUES (:fec_proceso)");
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
    }

    @Override
    public void write(List<? extends Ariadna> items) throws Exception {
        super.write(items);
        int count = 0;
        for (Ariadna item : items) {
            if (item != null) {
                String sql = "INSERT INTO ariadna (codestr, producto, descripcion) " +
                             "VALUES (:codestr, :producto, :descripcion) " +
                             "ON DUPLICATE KEY UPDATE codestr = :codestr";

                jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(item));
                count++;
            }
        }
        logger.info("Number of records written to the database: {}", count);
    }
}


//@Component
//public class UniqueAriadnaWriter extends JdbcBatchItemWriter<Ariadna> {
//
//    private static final Logger logger = LoggerFactory.getLogger(UniqueAriadnaWriter.class);
//
//    private final NamedParameterJdbcTemplate jdbcTemplate;
//    private final AtomicInteger recordsInserted = new AtomicInteger(0); // Contador de registros
//
//    public UniqueAriadnaWriter(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//        setDataSource(dataSource);
//        setSql("INSERT IGNORE INTO ariadna (codestr, producto, descripcion) VALUES (:codestr, :producto, :descripcion)");
//        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//    }
//
//    @Override
//    public void write(List<? extends Ariadna> items) throws Exception {
//        super.write(items);
//        int insertedCount = items.size();
//        recordsInserted.addAndGet(insertedCount);
//        logger.info("Se han insertado {} registros en este batch. Total acumulado: {}", insertedCount, recordsInserted.get());
//    }
//
//}


