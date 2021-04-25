package com.springbatch.springbatch.writer;

import com.springbatch.springbatch.domain.ArquivoEntrada;
import com.springbatch.springbatch.repository.custom.JdbcTemplateBulkOperations;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {

    @Autowired
    JdbcTemplateBulkOperations bulkOperations;

    private JdbcTemplate jdbcTemplate;

    public LeituraArquivoLarguraFixaWriterConfig(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Bean
    public ItemWriter<ArquivoEntrada> leituraArquivoLarguraFixaWriter(@Qualifier("springDataSource") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ArquivoEntrada>()
                .dataSource(dataSource)
                .sql("INSERT INTO ARQUIVO_ENTRADA (codigo, descricao, texto01, texto02, texto03, texto04 ) VALUES (?,?,?,?,?,?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();

    }

    private ItemPreparedStatementSetter<ArquivoEntrada> itemPreparedStatementSetter() {
        return new ItemPreparedStatementSetter<ArquivoEntrada>() {
            @Override
            public void setValues(ArquivoEntrada arquivoEntrada, PreparedStatement ps) throws SQLException {

                ps.setString(1, arquivoEntrada.getCodigo());
                ps.setString(2, arquivoEntrada.getDescricao());
                ps.setString(3, arquivoEntrada.getTexto01());
                ps.setString(4, arquivoEntrada.getTexto02());
                ps.setString(5, arquivoEntrada.getTexto03());
                ps.setString(6, arquivoEntrada.getTexto04());
            }
        };
    }
}