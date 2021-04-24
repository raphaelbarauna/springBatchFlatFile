package com.springbatch.springbatch.processor;

import com.springbatch.springbatch.domain.ArquivoEntrada;
import org.springframework.batch.item.ItemProcessor;

public class ArquivoProcessor implements ItemProcessor<ArquivoEntrada, ArquivoEntrada> {

    @Override
    public ArquivoEntrada process(ArquivoEntrada arquivoEntrada) throws Exception {

        ArquivoEntrada novoArquivo = new ArquivoEntrada();

       // novoArquivo.setTexto(arquivoEntrada.getTexto());

        return novoArquivo;
    }
}
