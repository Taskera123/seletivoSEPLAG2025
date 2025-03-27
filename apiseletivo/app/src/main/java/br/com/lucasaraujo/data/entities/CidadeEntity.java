package br.com.lucasaraujo.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class CidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "cidade_seq")
    @SequenceGenerator( name = "cidade_seq", sequenceName = "cidade_cid_id_seq", allocationSize = 1)
    @Column(name = "cid_id")
    private Long cidadeId;

    @Column(name = "cid_nome", length = 200, nullable = false)
    private String cidadeNome;

    @Column(name = "cid_uf", length = 2, nullable = false)
    private String cidadeUf;

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getCidadeNome() {
        return cidadeNome;
    }

    public void setCidadeNome(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public String getCidadeUf() {
        return cidadeUf;
    }

    public void setCidadeUf(String cidadeUf) {
        this.cidadeUf = cidadeUf;
    }

    public CidadeEntity() {
    }

    public CidadeEntity(Long cidadeId, String cidadeNome, String cidadeUf) {
        this.cidadeId = cidadeId;
        this.cidadeNome = cidadeNome;
        this.cidadeUf = cidadeUf;
    }

    public CidadeEntity(String cidadeNome, String cidadeUf) {
        this.cidadeNome = cidadeNome;
        this.cidadeUf = cidadeUf;
    }
}
