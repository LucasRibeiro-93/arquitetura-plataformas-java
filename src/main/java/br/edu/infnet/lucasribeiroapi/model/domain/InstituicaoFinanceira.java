package br.edu.infnet.lucasribeiroapi.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class InstituicaoFinanceira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cnpj;
    private Integer codigoTipo;
    private Boolean ativo;
    private Integer codigoBanco;
    private Date dataCriacao;
    private Date dataAtualizacao;
}