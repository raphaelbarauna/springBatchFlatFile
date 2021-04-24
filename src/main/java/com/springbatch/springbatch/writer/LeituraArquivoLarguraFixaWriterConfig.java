package com.springbatch.springbatch.writer;

import com.springbatch.springbatch.domain.ArquivoEntrada;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {

    @Bean
    public ItemWriter<ArquivoEntrada> leituraArquivoLarguraFixaWriter() {
        return items -> items.forEach(System.out::println);

    }
}