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
import java.sql.Date;
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
                .sql("INSERT INTO ARQUIVO_ENTRADA (TIP_REGISTRO, MODELO_NF, NUMERO_NF, SERIE_NF, DT_EMISSAO, CHAVE_AUTENTICACAO, CPF_CNPJ, INSCRICAO_ESTADUAL, RAZAO_SOCIAL, COD_IDENTIFICACAO_SERV, VLR_TOTAL, VLR_BASE_ICMS,\n" +
                        "VLR_ICMS, NUM_ITEM_ESTORNADO, VLR_ITEM_ESTORNADO, VLR_ICMS_ESTORNADO, HIPOTESE_ESTORNO, MOTIVO_ESTORNO, NUM_CONTROLE_RECLAMACAO) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();

    }

    private ItemPreparedStatementSetter<ArquivoEntrada> itemPreparedStatementSetter() {
        return new ItemPreparedStatementSetter<ArquivoEntrada>() {
            @Override
            public void setValues(ArquivoEntrada arquivoEntrada, PreparedStatement ps) throws SQLException {

                ps.setString(1, arquivoEntrada.getTIP_REGISTRO());
                ps.setString(2, arquivoEntrada.getMODELO_NF());
                ps.setString(3, arquivoEntrada.getNUMERO_NF());
                ps.setString(4, arquivoEntrada.getSERIE_NF());
                ps.setString(5, arquivoEntrada.getDT_EMISSAO());

                ps.setString(6, arquivoEntrada.getCHAVE_AUTENTICACAO());
                ps.setString(7, arquivoEntrada.getCPF_CNPJ());
                ps.setString(8, arquivoEntrada.getINSCRICAO_ESTADUAL());
                ps.setString(9, arquivoEntrada.getRAZAO_SOCIAL());
                ps.setString(10, arquivoEntrada.getCOD_IDENTIFICACAO_SERV());

                ps.setLong(11, arquivoEntrada.getVLR_TOTAL());
                ps.setLong(12, arquivoEntrada.getVLR_BASE_ICMS());
                ps.setLong(13, arquivoEntrada.getVLR_ICMS());
                ps.setLong(14, arquivoEntrada.getNUM_ITEM_ESTORNADO());
                ps.setLong(15, arquivoEntrada.getVLR_ITEM_ESTORNADO());

                ps.setLong(16, arquivoEntrada.getVLR_ICMS_ESTORNADO());
                ps.setString(17, arquivoEntrada.getHIPOTESE_ESTORNO());
                ps.setString(18, arquivoEntrada.getMOTIVO_ESTORNO());
                ps.setString(19, arquivoEntrada.getNUM_CONTROLE_RECLAMACAO());

            }
        };
    }
}