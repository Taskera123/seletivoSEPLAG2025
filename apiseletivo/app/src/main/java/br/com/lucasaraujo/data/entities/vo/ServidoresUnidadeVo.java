package br.com.lucasaraujo.data.entities.vo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

public class ServidoresUnidadeVo {

    private String nome;

    private LocalDate dataNascimento;

    private String nomeUnidade;

    private Long pessoaId;

    private Set<String> listLinkFotos;

    public ServidoresUnidadeVo() {
    }

    public ServidoresUnidadeVo(String nome, String nomeUnidade,LocalDate dataNascimento,  Long pessoaId) {
        this.nome = nome;
        this.nomeUnidade = nomeUnidade;
        this.dataNascimento = dataNascimento;
        this.pessoaId = pessoaId;
    }

    public ServidoresUnidadeVo(String nome, String nomeUnidade) {
        this.nome = nome;
        this.nomeUnidade = nomeUnidade;
    }

    public ServidoresUnidadeVo(String nome, String nomeUnidade,LocalDate dataNascimento) {
        this.nome = nome;
        this.nomeUnidade = nomeUnidade;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public Set<String> getlistLinkFotos() {
        return listLinkFotos;
    }

    public void setlistLinkFotos(Set<String> listLinkFotos) {
        this.listLinkFotos = listLinkFotos;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public int getIdade() {
        if (this.dataNascimento == null) {
            return 0;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}
