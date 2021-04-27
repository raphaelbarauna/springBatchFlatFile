package com.springbatch.springbatch.domain;

import lombok.*;
import oracle.sql.DATE;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ArquivoEntrada {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String TIP_REGISTRO;
    private String MODELO_NF;
    private String NUMERO_NF;
    private String SERIE_NF;
    private String DT_EMISSAO;
    private String CHAVE_AUTENTICACAO;
    private String CPF_CNPJ;
    private String INSCRICAO_ESTADUAL;
    private String RAZAO_SOCIAL;
    private String COD_IDENTIFICACAO_SERV;
    private Long VLR_TOTAL;
    private Long VLR_BASE_ICMS;
    private Long VLR_ICMS;
    private Long NUM_ITEM_ESTORNADO;
    private Long VLR_ITEM_ESTORNADO;
    private Long VLR_ICMS_ESTORNADO;
    private String HIPOTESE_ESTORNO;
    private String MOTIVO_ESTORNO;
    private String NUM_CONTROLE_RECLAMACAO;

    public ArquivoEntrada(String TIP_REGISTRO, String MODELO_NF, String NUMERO_NF, String SERIE_NF, String DT_EMISSAO, String CHAVE_AUTENTICACAO, String CPF_CNPJ, String INSCRICAO_ESTADUAL, String RAZAO_SOCIAL, String COD_IDENTIFICACAO_SERV, Long VLR_TOTAL, Long VLR_BASE_ICMS, Long VLR_ICMS, Long NUM_ITEM_ESTORNADO, Long VLR_ITEM_ESTORNADO, Long VLR_ICMS_ESTORNADO, String HIPOTESE_ESTORNO, String MOTIVO_ESTORNO, String NUM_CONTROLE_RECLAMACAO) {
        this.TIP_REGISTRO = TIP_REGISTRO;
        this.MODELO_NF = MODELO_NF;
        this.NUMERO_NF = NUMERO_NF;
        this.SERIE_NF = SERIE_NF;
        this.DT_EMISSAO = DT_EMISSAO;
        this.CHAVE_AUTENTICACAO = CHAVE_AUTENTICACAO;
        this.CPF_CNPJ = CPF_CNPJ;
        this.INSCRICAO_ESTADUAL = INSCRICAO_ESTADUAL;
        this.RAZAO_SOCIAL = RAZAO_SOCIAL;
        this.COD_IDENTIFICACAO_SERV = COD_IDENTIFICACAO_SERV;
        this.VLR_TOTAL = VLR_TOTAL;
        this.VLR_BASE_ICMS = VLR_BASE_ICMS;
        this.VLR_ICMS = VLR_ICMS;
        this.NUM_ITEM_ESTORNADO = NUM_ITEM_ESTORNADO;
        this.VLR_ITEM_ESTORNADO = VLR_ITEM_ESTORNADO;
        this.VLR_ICMS_ESTORNADO = VLR_ICMS_ESTORNADO;
        this.HIPOTESE_ESTORNO = HIPOTESE_ESTORNO;
        this.MOTIVO_ESTORNO = MOTIVO_ESTORNO;
        this.NUM_CONTROLE_RECLAMACAO = NUM_CONTROLE_RECLAMACAO;
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
                "TIP_REGISTRO='" + TIP_REGISTRO + '\'' +
                ", MODELO_NF='" + MODELO_NF + '\'' +
                ", NUMERO_NF='" + NUMERO_NF + '\'' +
                ", SERIE_NF='" + SERIE_NF + '\'' +
                ", DT_EMISSAO=" + DT_EMISSAO +
                ", CHAVE_AUTENTICACAO='" + CHAVE_AUTENTICACAO + '\'' +
                ", CPF_CNPJ='" + CPF_CNPJ + '\'' +
                ", INSCRICAO_ESTADUAL='" + INSCRICAO_ESTADUAL + '\'' +
                ", RAZAO_SOCIAL='" + RAZAO_SOCIAL + '\'' +
                ", COD_IDENTIFICACAO_SERV='" + COD_IDENTIFICACAO_SERV + '\'' +
                ", VLR_TOTAL=" + VLR_TOTAL +
                ", VLR_BASE_ICMS=" + VLR_BASE_ICMS +
                ", VLR_ICMS=" + VLR_ICMS +
                ", NUM_ITEM_ESTORNADO=" + NUM_ITEM_ESTORNADO +
                ", VLR_ITEM_ESTORNADO=" + VLR_ITEM_ESTORNADO +
                ", VLR_ICMS_ESTORNADO=" + VLR_ICMS_ESTORNADO +
                ", HIPOTESE_ESTORNO='" + HIPOTESE_ESTORNO + '\'' +
                ", MOTIVO_ESTORNO='" + MOTIVO_ESTORNO + '\'' +
                ", NUM_CONTROLE_RECLAMACAO='" + NUM_CONTROLE_RECLAMACAO + '\'' +
                '}';
    }
}

