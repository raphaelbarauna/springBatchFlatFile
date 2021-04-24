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
    @Bean
    public FlatFileItemReader<ArquivoEntrada> leituraArquivoLarguraFixaReader(){
        FlatFileItemReaderBuilder<ArquivoEntrada> flatFileItemReader = new FlatFileItemReaderBuilder<>();
        flatFileItemReader.linesToSkip(1)
                .name("leituraArquivoLarguraFixaReader")
                .resource(inputResource)
                .fixedLength()
                .columns(new Range(1, 14),new Range(15, 83), new Range(84, 118), new Range(119, 194),new Range(195, 394),new Range(395, 414))
                .names("CODIGO", "DESCRICAO", "TEXTO1", "TEXTO2","TEXTO3","TEXTO4")
                .targetType(ArquivoEntrada.class);

        return flatFileItemReader.build();

    }
}
