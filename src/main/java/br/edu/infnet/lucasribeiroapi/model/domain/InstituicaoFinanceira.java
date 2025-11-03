package br.edu.infnet.lucasribeiroapi.model.domain;

import lombok.Data;

import java.util.Date;

@Data
public class InstituicaoFinanceira {
    private Integer id;
    private String nome;
    private String cnpj;
    private Integer codigoTipo;
    private Boolean ativo;
    private Integer codigoBanco;
    private Date dataCriacao;
    private Date dataAtualizacao;
}
