package br.com.lucasaraujo.data.entities;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "unidade")
public class UnidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "unidade_seq")
    @SequenceGenerator( name = "unidade_seq", sequenceName = "unidade_unid_id_seq", allocationSize = 1)
    @Column(name = "unid_id")
    private Long unidadeId;

    @Column(name = "unid_nome", length = 200, nullable = false)
    private String unidadeNome;

    @Column(name = "unid_sigla", length = 20, nullable = false)
    private String unidadeSigla;

    @Transient
    Set<EnderecoEntity> enderecoList;

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public String getUnidadeNome() {
        return unidadeNome;
    }

    public void setUnidadeNome(String unidadeNome) {
        this.unidadeNome = unidadeNome;
    }

    public String getUnidadeSigla() {
        return unidadeSigla;
    }

    public Set<EnderecoEntity> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(Set<EnderecoEntity> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public void setUnidadeSigla(String unidadeSigla) {
        this.unidadeSigla = unidadeSigla;
    }

    public UnidadeEntity() {
    }

    public UnidadeEntity(Long unidadeId, String unidadeNome, String unidadeSigla) {
        this.unidadeId = unidadeId;
        this.unidadeNome = unidadeNome;
        this.unidadeSigla = unidadeSigla;
    }

    public UnidadeEntity(String unidadeNome, String unidadeSigla) {
        this.unidadeNome = unidadeNome;
        this.unidadeSigla = unidadeSigla;
    }

    public UnidadeEntity(Long unidadeId, String unidadeNome, String unidadeSigla,Set<EnderecoEntity> enderecoList) {
        this.unidadeId = unidadeId;
        this.unidadeNome = unidadeNome;
        this.unidadeSigla = unidadeSigla;
        this.enderecoList = enderecoList;
    }

    public UnidadeEntity(String unidadeNome, String unidadeSigla,Set<EnderecoEntity> enderecoList) {
        this.unidadeId = unidadeId;
        this.unidadeNome = unidadeNome;
        this.unidadeSigla = unidadeSigla;
        this.enderecoList = enderecoList;
    }

}

