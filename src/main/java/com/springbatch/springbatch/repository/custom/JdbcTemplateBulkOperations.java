package com.springbatch.springbatch.repository.custom;

import com.springbatch.springbatch.domain.ArquivoEntrada;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Configuration
public class JdbcTemplateBulkOperations implements BulkOperations{

    private JdbcTemplate jdbcTemplate;


    public JdbcTemplateBulkOperations(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    @Transactional
    public void bulkPersist(final List<ArquivoEntrada> entities) {

        jdbcTemplate.batchUpdate("INSERT INTO ARQUIVO_ENTRADA (codigo, descricao," +
                "texto01, texto02, texto03, texto04 ) VALUES (?,?,?,?,?,?)", new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                //ps.setLong(1, i);
//                ps.setString(1, entities.get(i).getCodigo());
//                ps.setString(2, entities.get(i).getDescricao());
//                ps.setString(3, entities.get(i).getTexto01());
//                ps.setString(4, entities.get(i).getTexto02());
//                ps.setString(5, entities.get(i).getTexto03());
//                ps.setString(6, entities.get(i).getTexto04());
            }

            @Override
            public int getBatchSize() {
                return entities.size();
            }
        });
    }
}
