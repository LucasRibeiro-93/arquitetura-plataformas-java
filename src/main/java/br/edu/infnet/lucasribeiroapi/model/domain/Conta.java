package br.edu.infnet.lucasribeiroapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;
    private String tipo;
    private Boolean ativo;
    private Integer idDepartamento;
    private Date dataCriacao;
    private Date dataAtualizacao;
}