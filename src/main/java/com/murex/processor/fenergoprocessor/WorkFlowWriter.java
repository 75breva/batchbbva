package com.murex.processor.fenergoprocessor;

import com.murex.model.fenergo.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class WorkFlowWriter implements ItemWriter<List<Workflow>> {

    private static final Logger LOG = LoggerFactory.getLogger(WorkFlowWriter.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WorkFlowWriter(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void write(List<? extends List<Workflow>> items) throws Exception {
        for(List<Workflow> item : items){
            if(item != null){
                System.out.println("Entidad procesada: " + item);
            }
        }

/*        items.stream()
                .flatMap(List::stream)  // Aplanamos la lista de listas a una lista de Workflow
                .filter(Objects::nonNull)  // Filtramos los elementos nulos
                .forEach(workflow -> jdbcTemplate.update(
                        "INSERT INTO workflow (case_type,case_type_status,case_created_date) VALUES (:case_Type,:case_type_status,:case_created_date) " +
                                "ON DUPLICATE KEY UPDATE case_type = case_type",
                        new BeanPropertySqlParameterSource(workflow)
                ));
*/

        LOG.info("Writing WorkFlow information: {}", items);


// En lugar de ejecutar una consulta SQL por cada objeto (ineficiente), ahora insertamos todos los objetos en una sola consulta.

/*        List<Workflow> workflows = items.stream()   // Aplanamos la lista de listas a una única lista de Workflow
                .flatMap(List::stream)
                .filter(Objects::nonNull)           //para evitar NullPointerException.
                .toList();                          // para manejar bien la lista antes de la inserción.
        if (!workflows.isEmpty()) {
            String sql = "INSERT INTO workflow (case_type, case_type_status, case_created_date) " +
                    "VALUES (:case_type, :case_type_status, :case_created_date) " +
                    "ON DUPLICATE KEY UPDATE case_type = VALUES(case_type)";
            SqlParameterSource[] batchParams = workflows.stream()
                    .map(BeanPropertySqlParameterSource::new)
                    .toArray(SqlParameterSource[]::new);

            jdbcTemplate.batchUpdate(sql, batchParams);

            LOG.info("Inserted/Updated {} Workflow records", workflows.size());
        }*/
    }
}

