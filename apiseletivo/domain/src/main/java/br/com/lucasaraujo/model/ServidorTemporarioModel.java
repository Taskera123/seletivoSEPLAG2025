package br.com.lucasaraujo.model;

import java.time.LocalDate;


public class ServidorTemporarioModel {

    private Long id;

    private LocalDate servidorTemporarioDataAdmissao;

    private LocalDate servidorTemporarioDataDemissao;

    private PessoaModel pessoa;

    public PessoaModel getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaModel pessoa) {
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

    public ServidorTemporarioModel() {
    }

    public ServidorTemporarioModel(Long id, LocalDate servidorTemporarioDataAdmissao, LocalDate servidorTemporarioDataDemissao, PessoaModel pessoa) {
        this.id = id;
        this.servidorTemporarioDataAdmissao = servidorTemporarioDataAdmissao;
        this.servidorTemporarioDataDemissao = servidorTemporarioDataDemissao;
        this.pessoa = pessoa;
    }

    public ServidorTemporarioModel(LocalDate servidorTemporarioDataAdmissao, LocalDate servidorTemporarioDataDemissao, PessoaModel pessoa) {
        this.servidorTemporarioDataAdmissao = servidorTemporarioDataAdmissao;
        this.servidorTemporarioDataDemissao = servidorTemporarioDataDemissao;
        this.pessoa = pessoa;
    }
}
