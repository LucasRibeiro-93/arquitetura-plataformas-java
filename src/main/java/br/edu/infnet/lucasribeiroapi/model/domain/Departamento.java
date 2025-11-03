package br.edu.infnet.lucasribeiroapi.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;
    private Boolean ativo;
    private Integer idInstituicaoFinanceira;
    private Date dataCriacao;
    private Date dataAtualizacao;
}