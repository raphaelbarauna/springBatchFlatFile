package com.springbatch.springbatch.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class ArquivoEntrada {

    private String codigo;

    private String descricao;

    private String texto01;

    private String texto02;

    private String texto03;

    private String texto04;

    public ArquivoEntrada(String codigo, String descricao, String texto01, String texto02, String texto03, String texto04) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.texto01 = texto01;
        this.texto02 = texto02;
        this.texto03 = texto03;
        this.texto04 = texto04;
    }
/*    @SneakyThrows
    public ArquivoEntrada (final String line) {
        this.codigo = line.substring(1, 13);
        this.descricao = line.substring(14, 60);
        this.texto01 = line.substring(69, 104);
        this.texto02 = line.substring(104, 219);
    }*/

    @Override
    public String toString() {
        return "ArquivoEntrada{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", texto01='" + texto01 + '\'' +
                ", texto02='" + texto02 + '\'' +
                ", texto03='" + texto03 + '\'' +
                ", texto04='" + texto04 + '\'' +
                '}';
    }
}

