package br.edu.infnet.lucasribeiroapi.model.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Conta {
    private Integer id;
    private String numero;
    private String tipo;
    private Boolean ativo;
    private Integer idDepartamento;
    private Date dataCriacao;
    private Date dataAtualizacao;
}
