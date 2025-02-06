package com.murex.model.pruebaJson;

import com.murex.model.pruebaJson.model.WorkflowMy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkflowMyDatabaseWriter implements ItemWriter<WorkflowMy> {

    private static final Logger LOG = LoggerFactory.getLogger(WorkflowMyDatabaseWriter.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT =
            "INSERT INTO workflow (isin, quantity, customer) " +
                    "VALUES (:isin, :quantity, :customer) " +
                    "ON DUPLICATE KEY UPDATE isin = VALUES(isin)";

    @Override
    public void write(List<? extends WorkflowMy> items) throws Exception {
        int count = 0;
        for (WorkflowMy item : items) {
            if (item != null) {
                jdbcTemplate.update(SQL_INSERT, new BeanPropertySqlParameterSource(item));
                count++;
            }
        }
        LOG.info("Número de registros insertados en la base de datos: {}", count);
    }
}
