package br.com.lucasaraujo.model;


import java.util.Set;

public class UnidadeModel {

    private Long unidadeId;

    private String unidadeNome;

    private String unidadeSigla;

    private Set<EnderecoModel> enderecoList;

    private Set<Long>enderecoIdList;

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

    public void setUnidadeSigla(String unidadeSigla) {
        this.unidadeSigla = unidadeSigla;
    }

    public Set<Long> getEnderecoIdList() {
        return enderecoIdList;
    }

    public void setEnderecoIdList(Set<Long> enderecoIdList) {
        this.enderecoIdList = enderecoIdList;
    }

    public Set<EnderecoModel> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(Set<EnderecoModel> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public UnidadeModel() {
    }

    public UnidadeModel(Long unidadeId, String unidadeNome, String unidadeSigla, Set<EnderecoModel> enderecoList) {
        this.unidadeId = unidadeId;
        this.unidadeNome = unidadeNome;
        this.unidadeSigla = unidadeSigla;
        this.enderecoList = enderecoList;
    }

    public UnidadeModel(String unidadeNome, String unidadeSigla, Set<Long> enderecoIdList) {
        this.unidadeNome = unidadeNome;
        this.unidadeSigla = unidadeSigla;
        this.enderecoIdList = enderecoIdList;
    }

    public UnidadeModel(Long unidadeId,String unidadeNome, String unidadeSigla) {
        this.unidadeId = unidadeId;
        this.unidadeNome = unidadeNome;
        this.unidadeSigla = unidadeSigla;
    }

    public UnidadeModel(String unidadeNome, String unidadeSigla) {
        this.unidadeNome = unidadeNome;
        this.unidadeSigla = unidadeSigla;
    }

}

