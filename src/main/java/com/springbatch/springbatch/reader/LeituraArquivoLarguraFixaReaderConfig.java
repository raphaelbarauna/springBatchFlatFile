package com.springbatch.springbatch.reader;

import com.springbatch.springbatch.domain.ArquivoEntrada;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoLarguraFixaReaderConfig {

    private Resource inputResource = new FileSystemResource("data/202004EN.txt");

    @StepScope
    @Bean(name = "readerBean")
    public FlatFileItemReader<ArquivoEntrada> leituraArquivoLarguraFixaReader() {

        FlatFileItemReaderBuilder<ArquivoEntrada> flatFileItemReader = new FlatFileItemReaderBuilder<>();
        flatFileItemReader.linesToSkip(1)
                .name("leituraArquivoLarguraFixaReader")
                .resource(inputResource)
                .fixedLength()
                //Delimita os campos através do range
                .columns(new Range(1, 1), new Range(2, 3), new Range(4, 12), new Range(13, 14), new Range(15, 23), new Range(24, 55),
                        new Range(56, 69), new Range(70, 83), new Range(84, 118), new Range(119, 130), new Range(131, 142)
                        , new Range(143, 154), new Range(155, 166), new Range(167, 169), new Range(170, 181)
                        , new Range(182, 193), new Range(194, 194), new Range(195, 394), new Range(395, 414))
                .names("TIP_REGISTRO", "MODELO_NF", "NUMERO_NF", "SERIE_NF", "DT_EMISSAO", "CHAVE_AUTENTICACAO", "CPF_CNPJ",
                        "INSCRICAO_ESTADUAL", "RAZAO_SOCIAL", "COD_IDENTIFICACAO_SERV", "VLR_TOTAL", "VLR_BASE_ICMS",
                        "VLR_ICMS", "NUM_ITEM_ESTORNADO", "VLR_ITEM_ESTORNADO", "VLR_ICMS_ESTORNADO",
                        "HIPOTESE_ESTORNO", "MOTIVO_ESTORNO", "NUM_CONTROLE_RECLAMACAO")
                .targetType(ArquivoEntrada.class);

        return flatFileItemReader.build();

    }
}
