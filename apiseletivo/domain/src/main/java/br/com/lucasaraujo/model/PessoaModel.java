package br.com.lucasaraujo.model;

import java.time.LocalDate;
import java.util.Set;


public class PessoaModel {

    private Long pessoaId;

    private String pessoaNome;

    private LocalDate pessoaDataNascimento;

    private Set<EnderecoModel> enderecoList;

    private Set<Long>enderecoIdList;

    private String pessoaSexo;

    private String pessoaMae;

    private String pessoaPai;

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    public LocalDate getPessoaDataNascimento() {
        return pessoaDataNascimento;
    }

    public void setPessoaDataNascimento(LocalDate pessoaDataNascimento) {
        this.pessoaDataNascimento = pessoaDataNascimento;
    }

    public String getPessoaSexo() {
        return pessoaSexo;
    }

    public void setPessoaSexo(String pessoaSexo) {
        this.pessoaSexo = pessoaSexo;
    }

    public String getPessoaMae() {
        return pessoaMae;
    }

    public void setPessoaMae(String pessoaMae) {
        this.pessoaMae = pessoaMae;
    }

    public String getPessoaPai() {
        return pessoaPai;
    }

    public void setPessoaPai(String pessoaPai) {
        this.pessoaPai = pessoaPai;
    }

    public Set<EnderecoModel> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(Set<EnderecoModel> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public Set<Long> getEnderecoIdList() {
        return enderecoIdList;
    }

    public void setEnderecoIdList(Set<Long> enderecoIdList) {
        this.enderecoIdList = enderecoIdList;
    }

    public PessoaModel() {

    }

    public PessoaModel(Long pessoaId, String pessoaNome, LocalDate pessoaDataNascimento, Set<EnderecoModel> enderecoList, String pessoaSexo, String pessoaMae, String pessoaPai) {
        this.pessoaId = pessoaId;
        this.pessoaNome = pessoaNome;
        this.pessoaDataNascimento = pessoaDataNascimento;
        this.enderecoList = enderecoList;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
    }


    public PessoaModel(String pessoaNome, LocalDate pessoaDataNascimento, Set<Long> enderecoIdList, String pessoaSexo, String pessoaMae, String pessoaPai) {
        this.pessoaNome = pessoaNome;
        this.pessoaDataNascimento = pessoaDataNascimento;
        this.enderecoIdList = enderecoIdList;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
    }



    public PessoaModel(String pessoaNome, LocalDate pessoaDataNascimento, String pessoaSexo, String pessoaMae, String pessoaPai) {
        this.pessoaNome = pessoaNome;
        this.pessoaDataNascimento = pessoaDataNascimento;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
    }
}
