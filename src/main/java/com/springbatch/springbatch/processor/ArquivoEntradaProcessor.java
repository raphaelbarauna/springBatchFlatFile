package com.springbatch.springbatch.processor;

import com.springbatch.springbatch.domain.ArquivoEntrada;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;

public class ArquivoEntradaProcessor  {

    @Bean
    public FunctionItemProcessor<ArquivoEntrada, ArquivoEntrada> process(ArquivoEntrada arquivoEntrada) throws Exception {



        return null;
    }

}
