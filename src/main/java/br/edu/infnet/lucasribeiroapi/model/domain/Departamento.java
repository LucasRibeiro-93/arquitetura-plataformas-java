package br.edu.infnet.lucasribeiroapi.model.domain;


import lombok.Data;

import java.util.Date;

@Data
public class Departamento {
    private Integer id;
    private String nome;
    private String descricao;
    private Boolean ativo;
    private Integer idInstituicaoFinanceira;
    private Date dataCriacao;
    private Date dataAtualizacao;
}
