package br.com.lucasaraujo.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
public class ServidorTemporarioEntity {

    @Id
    @Column(name = "pes_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @Column(name = "st_data_admissao")
    private LocalDate servidorTemporarioDataAdmissao;

    @Column(name = "st_data_demissao")
    private LocalDate servidorTemporarioDataDemissao;

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getServidorTemporarioDataAdmissao() {
        return servidorTemporarioDataAdmissao;
    }

    public void setServidorTemporarioDataAdmissao(LocalDate servidorTemporarioDataAdmissao) {
        this.servidorTemporarioDataAdmissao = servidorTemporarioDataAdmissao;
    }

    public LocalDate getServidorTemporarioDataDemissao() {
        return servidorTemporarioDataDemissao;
    }

    public void setServidorTemporarioDataDemissao(LocalDate servidorTemporarioDataDemissao) {
        this.servidorTemporarioDataDemissao = servidorTemporarioDataDemissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServidorTemporarioEntity() {
    }

    public ServidorTemporarioEntity(Long id, LocalDate servidorTemporarioDataAdmissao, LocalDate servidorTemporarioDataDemissao, PessoaEntity pessoa) {
        this.id = id;
        this.servidorTemporarioDataAdmissao = servidorTemporarioDataAdmissao;
        this.servidorTemporarioDataDemissao = servidorTemporarioDataDemissao;
        this.pessoa = pessoa;
    }

    public ServidorTemporarioEntity(LocalDate servidorTemporarioDataAdmissao, LocalDate servidorTemporarioDataDemissao, PessoaEntity pessoa) {
        this.servidorTemporarioDataAdmissao = servidorTemporarioDataAdmissao;
        this.servidorTemporarioDataDemissao = servidorTemporarioDataDemissao;
        this.pessoa = pessoa;
    }
}
