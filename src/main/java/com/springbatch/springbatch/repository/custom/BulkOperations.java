package com.springbatch.springbatch.repository.custom;

import com.springbatch.springbatch.domain.ArquivoEntrada;

import java.util.List;

public interface BulkOperations {

    public void bulkPersist(List<ArquivoEntrada> lines);
}
