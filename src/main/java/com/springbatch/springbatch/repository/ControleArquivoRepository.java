package com.springbatch.springbatch.repository;

import com.springbatch.springbatch.domain.ControleArquivo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ControleArquivoRepository extends CrudRepository<ControleArquivo, Integer> {

    Optional<ControleArquivo> findByName(String name);

}
