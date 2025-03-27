package br.com.lucasaraujo.model;

import java.time.LocalDate;
import java.util.Set;


public class PessoaModel {

    private Long pessoaId;

    private String pesNome;

    private LocalDate pesDataNascimento;

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

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public LocalDate getPesDataNascimento() {
        return pesDataNascimento;
    }

    public void setPesDataNascimento(LocalDate pesDataNascimento) {
        this.pesDataNascimento = pesDataNascimento;
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

    public PessoaModel(Long pessoaId, String pesNome, LocalDate pesDataNascimento, Set<EnderecoModel> enderecoList, String pessoaSexo, String pessoaMae, String pessoaPai) {
        this.pessoaId = pessoaId;
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.enderecoList = enderecoList;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
    }


    public PessoaModel(String pesNome, LocalDate pesDataNascimento, Set<Long> enderecoIdList, String pessoaSexo, String pessoaMae, String pessoaPai) {
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.enderecoIdList = enderecoIdList;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
    }



    public PessoaModel(String pesNome, LocalDate pesDataNascimento, String pessoaSexo, String pessoaMae, String pessoaPai) {
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
    }
}
